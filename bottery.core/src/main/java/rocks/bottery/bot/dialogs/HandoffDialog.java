/**
 * Copyright (C) 2016-2018 Harald Kuhn
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.bot.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.connector.GenericActivity;
import rocks.bottery.connector.IConnector;
import rocks.bottery.messaging.IReceiver;

/**
 * A dialog that hands off activities to another connector. Useful for bots which need "help from a human". Note that
 * most frameworks do not support this very well so e.g. the name of the bot cannot change.
 * 
 * This dialog keeps track of conversations
 * 
 * <pre>
 * TODO: 
 * - check wether some connectors allow the bot to invite the human and just leave instead of routing the data 
 * - find a way to "leave" handoff mode for a conversation (back to bot)
 * </pre>
 * 
 * @author Harald Kuhn
 */
public class HandoffDialog implements IDialog, IReceiver {

	private IConnector					 targetConnector;
	private ISession					 session;

	// FIXME: this violates statelessnes, check wether there is a better solution
	private Map<String, GenericActivity> replyMapping;

	public HandoffDialog(IConnector targetConnector) {
		this.targetConnector = targetConnector;
		targetConnector.register(this);
		replyMapping = new HashMap<>();
	}

	@Override
	public void handle(ISession session, IActivity request) {
		this.session = session;

		replyMapping.put(request.getConversation().getId(), session.getConnector().newReplyTo(request));

		Logger.getLogger(HandoffDialog.class).debug(session.getChannel() + " -> " + targetConnector.getChannel() + " " + request.getText());
		targetConnector.send(request);
	}

	@Override
	public void receive(IActivity activity, IConnector connector) {
		Logger.getLogger(HandoffDialog.class).debug(connector.getChannel() + " -> " + session.getChannel() + " " + activity.getText());
		try {
			GenericActivity reply = replyMapping.get(activity.getConversation().getId());
			Logger.getLogger(HandoffDialog.class).debug("found reply " + reply);
			// FIXME: also copy attachments etc., create an utility for it
			reply.setText(activity.getText());
			reply.setFrom(activity.getFrom());

			session.send(reply);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

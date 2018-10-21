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
package rocks.bottery.connector.handoff;

import java.util.List;

import org.apache.log4j.Logger;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.IDialog;
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

	private IBot			  bot;

	private IHandoffConnector targetConnector;

	private boolean			  targetConnectorRegistered	= false;

	public HandoffDialog(IHandoffConnector targetConnector) {
		this.targetConnector = targetConnector;
	}

	@Override
	public void handle(ISession session, IActivity request) {
		if (!targetConnectorRegistered) {
			targetConnector.register(this);
			targetConnectorRegistered = true;

			bot = session.getBot();

			List<IActivity> activitiesByConversation = session.getBot().getBotConfig().getArchive()
			        .getActivitiesByConversation(request.getConversation().getId());
			targetConnector.handoff(request.getConversation(), activitiesByConversation);

		}
		else {

			Logger.getLogger(HandoffDialog.class).debug("handle " + session.getChannel() + " -> " + targetConnector.getChannel() + " " + request.getText());

			targetConnector.send(request);
		}

	}

	@Override
	public void receive(IActivity activity, IConnector connector) {
		if (activity.getType() != ActivityType.MESSAGE) {
			return;
		}

		try {
			ISession session = bot.getBotConfig().getSessionStore().find(activity.getConversation().getId());

			Logger.getLogger(HandoffDialog.class).debug("receive " + connector.getChannel() + " -> " + session.getChannel() + " " + activity.getText());
			Logger.getLogger(HandoffDialog.class).debug(activity.getFrom().getAddress() + " > " + activity.getRecipient().getAddress());
			GenericActivity reply = connector.newMessageTo(activity.getRecipient());
			Logger.getLogger(HandoffDialog.class).debug("found reply " + reply);
			// FIXME: also copy attachments etc., create an utility for it
			reply.setText(activity.getText());
			reply.setFrom(activity.getFrom());
			reply.setConversation(activity.getConversation());

			session.send(reply);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

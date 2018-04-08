/**
 * Copyright (C) 2016-2017 Harald Kuhn
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

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.ISession;
import rocks.bottery.connector.IConnector;

/**
 * @author Harald Kuhn
 *
 */
public class HandoffDialog implements IDialog {

	private IConnector	 connector;
	private IParticipant target;

	@Override
	public void handle(ISession session, IActivity request) {
		IConnector targetConnector = session.getBot().getMessagingContext().getConnectorRegistry().get(target.getChannel());
		// request.setRecipient(target);
		targetConnector.send(request);
	}

}

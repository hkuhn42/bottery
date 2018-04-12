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
package rocks.bottery.bot;

import io.rincl.Rincled;
import rocks.bottery.connector.IConnector;
import rocks.bottery.messaging.IMessagingContext;
import rocks.bottery.messaging.IReceiver;

/**
 * Defines a generic bot
 * 
 * A bot is both listener and emitter of activities and acts like the controller of a conversation it keeps track of the
 * conversations via sessions and delegates the activities to dialogs based on the IRecognizers results
 * 
 * The actual handling and creation of Activities is normally done by instances of IDialog
 * 
 * The acutal connection to a messaging service is handled by a {@link IConnector}. Multiple connectors can be
 * associated with one bot but a connector instance may not be shared between different bots
 * 
 * @author Harald Kuhn
 */
public interface IBot extends IContext, IHandler, Rincled, IReceiver {

	public void invalidate(ISession session);

	/**
	 * Get the bots config
	 * 
	 * @return
	 */
	public IBotConfig getBotConfig();

	/**
	 * get the messaging context
	 * 
	 * @return
	 */
	public IMessagingContext getMessagingContext();
}

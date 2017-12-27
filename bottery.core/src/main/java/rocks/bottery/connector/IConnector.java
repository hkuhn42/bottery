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
package rocks.bottery.connector;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IParticipant;

/**
 * Defines a connector to a message api or message endpoint
 * 
 * One connector can only be associated with one bot and should only handle one account (participate in a chat as one
 * entity)
 * 
 * @author Harald Kuhn
 */
public interface IConnector {

	/**
	 * Register the bot on the connector. If multiple bots are connected, all are called it a activity (message , status
	 * change etc) is received.
	 * 
	 * @param handler
	 */
	public void register(IBot bot);

	/**
	 * shutdown (stop listen)
	 */
	public void shutdown();

	/**
	 * Send an activity to the api or endpoint
	 * 
	 * @param data
	 */
	public void send(IActivity activity);

	/**
	 * the participant (account) for the account currently used by this connector
	 * 
	 * @return
	 */
	public IParticipant getConnectorAccount();

	/**
	 * prepare a message to the given Participant (e.g. User, Bot, Group)
	 * 
	 * @param recipientId
	 * @return
	 */
	public IActivity newMessageTo(IParticipant recipientId);

	/**
	 * prepare an answer to the given Activity (e.g. User, Bot, Group)
	 * 
	 * this is a shortcut to newMessageTo and setRecipient calls
	 * 
	 * @param toThisActivity
	 *            the IActivity to answer to
	 * @return the answer IActivity
	 */
	public IActivity newReplyTo(IActivity toThisActivity);

	/**
	 * returns the unique channel for this connector
	 * 
	 * @return
	 */
	public String getChannel();
}

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
import rocks.bottery.bot.IParticipant;
import rocks.bottery.messaging.IMessagingConfig;
import rocks.bottery.messaging.IReceiver;

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
	 * Initialize this connector with the given config If a connector is registered to an bot, this method is
	 * automatically called
	 * 
	 * @param config
	 *            the IMessagingConfig instance to use
	 */
	public void init(IMessagingConfig config);

	/**
	 * Register the bot on the connector. If multiple bots are connected, all are called it a activity (message , status
	 * change etc) is received.
	 * 
	 * @param receiver
	 *            the IReceiver to register
	 */
	public void register(IReceiver receiver);

	/**
	 * shutdown (stop listen)
	 */
	public void shutdown();

	/**
	 * Send an activity to the api or endpoint
	 * 
	 * @param activitythe
	 *            IActivity to send via this connector
	 */
	public void send(IActivity activity);

	/**
	 * the participant (account) for the account currently used by this connector
	 * 
	 * @return the IParticipant representing the account of this connector with the underlying message system
	 */
	public IParticipant getConnectorAccount();

	/**
	 * prepare a message to the given Participant (e.g. User, Bot, Group)
	 * 
	 * @param recipientId
	 *            the IParticipant to receive this message
	 * @return a pre initialized ActivityBase instance
	 */
	public ActivityBase newMessageTo(IParticipant recipientId);

	/**
	 * prepare an answer to the given Activity (e.g. User, Bot, Group)
	 * 
	 * this is a shortcut to newMessageTo and setRecipient calls
	 * 
	 * @param toThisActivity
	 *            the IActivity to answer to
	 * @return the answer a pre initialized ActivityBase instance
	 */
	public GenericActivity newReplyTo(IActivity toThisActivity);

	/**
	 * returns the unique channel for this connector
	 * 
	 * @return the channel identifier
	 */
	public String getChannel();
}

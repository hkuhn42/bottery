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
package rocks.bottery.connector.console;

import java.util.Random;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericActivity;
import rocks.bottery.connector.GenericConversation;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.IConnector;
import rocks.bottery.messaging.IMessagingConfig;

/**
 * Base class for connectors which implements the factory methods for Activities and init
 * 
 * @author Harald Kuhn
 */
public abstract class ConnectorBase implements IConnector {

	protected IMessagingConfig config;

	@Override
	public void init(IMessagingConfig config) {
		this.config = config;
	}

	@Override
	public GenericActivity newMessageTo(IParticipant recipient) {
		GenericActivity activity = new GenericActivity();
		activity.setType(ActivityType.MESSAGE);
		GenericConversation conversation = new GenericConversation();
		conversation.setId(String.valueOf(this.hashCode()));
		conversation.setChannel(getChannel());
		activity.setConversation(conversation);
		activity.setFrom(getConnectorAccount());

		activity.setId(String.valueOf("c" + new Random().nextLong()));

		activity.setRecipient(new GenericParticipant());
		activity.getRecipient().setId(recipient.getId());
		activity.getRecipient().setName(recipient.getName());
		return activity;
	}

	@Override
	public GenericActivity newReplyTo(IActivity toThisActivity) {
		GenericActivity reply = newMessageTo(toThisActivity.getFrom());
		reply.setConversation(toThisActivity.getConversation());
		return reply;
	}

	@Override
	public void shutdown() {
		// some connectors to not need to shutdown explicitly
	}

}
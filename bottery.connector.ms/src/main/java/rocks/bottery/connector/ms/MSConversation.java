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
package rocks.bottery.connector.ms;

import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.connector.ms.model.ConversationAccount;

/**
 * @author Harald Kuhn
 *
 */
public class MSConversation implements IConversation {

	private ConversationAccount	conversation;
	private String				channel;

	MSConversation(ConversationAccount conversation, String channel) {
		this.conversation = conversation;
		this.channel = channel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConversation#getId()
	 */
	@Override
	public String getId() {
		return conversation.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConversation#getChannel()
	 */
	@Override
	public String getChannel() {
		return channel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConversation#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		return MSConnector.class.getName();
	}

	@Override
	public Object getConnectorConversation() {
		return conversation;
	}
}

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
package rocks.bottery.connector.stride;

import rocks.bottery.bot.IConversation;
import rocks.bottery.connector.stride.api.Conversation;

/**
 * A stride conversation
 * 
 * @author Harald Kuhn
 */
public class StrideConversation implements IConversation {

	private Conversation conversation;

	protected StrideConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getId()
	 */
	@Override
	public String getId() {
		return conversation.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getChannel()
	 */
	@Override
	public String getChannel() {
		return StrideConnector.CHANNEL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		return StrideConnector.class.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getConnectorConversation()
	 */
	@Override
	public Object getConnectorConversation() {
		return conversation;
	}

}

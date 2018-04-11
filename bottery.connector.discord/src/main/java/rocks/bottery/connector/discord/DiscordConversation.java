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
package rocks.bottery.connector.discord;

import rocks.bottery.bot.IConversation;
import sx.blah.discord.handle.obj.IChannel;

/**
 * @author Harald Kuhn
 *
 */
public class DiscordConversation implements IConversation {
	private IChannel channel;
	private String	 connectorId;

	public DiscordConversation(IChannel channel, String connectorId) {
		this.channel = channel;
		this.connectorId = connectorId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getId()
	 */
	@Override
	public String getId() {
		return channel.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getChannel()
	 */
	@Override
	public String getChannel() {
		return channel.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		return connectorId;
	}

	@Override
	public Object getConnectorConversation() {
		return channel;
	}
}

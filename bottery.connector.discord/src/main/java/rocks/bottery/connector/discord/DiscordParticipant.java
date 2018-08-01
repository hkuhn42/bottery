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

import rocks.bottery.bot.IParticipant;
import sx.blah.discord.handle.obj.IUser;

/**
 * The Participant of a discord chat
 * 
 * getConnectorParticipant gives access to sx.blah.discord.handle.obj.IUser
 * 
 * @author Harald Kuhn
 */
public class DiscordParticipant implements IParticipant {

	private IUser user;

	public DiscordParticipant(IUser user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getId()
	 */
	@Override
	public String getId() {
		return user.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getName()
	 */
	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public String getChannel() {
		return "discord";
	}

	@Override
	public Object getConnectorParticipant() {
		return user;
	}

	@Override
	public String getAddress() {
		return user.getDiscriminator();
	}
}

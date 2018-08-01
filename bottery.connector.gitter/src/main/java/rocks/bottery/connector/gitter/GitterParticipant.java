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
package rocks.bottery.connector.gitter;

import rocks.bottery.bot.IParticipant;

/**
 * A participant for a gitter user
 * 
 * @author Harald Kuhn
 */
public class GitterParticipant implements IParticipant {

	private User gitterUser;

	protected GitterParticipant(User gitterUser) {
		this.gitterUser = gitterUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getId()
	 */
	@Override
	public String getId() {
		return gitterUser.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getAddress()
	 */
	@Override
	public String getAddress() {
		return gitterUser.getUsername();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getName()
	 */
	@Override
	public String getName() {
		return gitterUser.getDisplayName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getChannel()
	 */
	@Override
	public String getChannel() {
		return GitterConnector.CHANNEL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getConnectorParticipant()
	 */
	@Override
	public Object getConnectorParticipant() {
		return gitterUser;
	}

}

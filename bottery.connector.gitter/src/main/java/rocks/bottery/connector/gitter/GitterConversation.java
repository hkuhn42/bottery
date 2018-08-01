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

import rocks.bottery.bot.IConversation;

/**
 * A gitter conversation (aka the room)
 * 
 * @author Harald Kuhn
 */
public class GitterConversation implements IConversation {

	private Room room;

	protected GitterConversation(Room room) {
		this.room = room;
	}

	@Override
	public String getId() {
		return room.getId();
	}

	@Override
	public String getChannel() {
		return GitterConnector.CHANNEL;
	}

	@Override
	public String getConnectorId() {
		return GitterConnector.class.getName();
	}

	@Override
	public Object getConnectorConversation() {
		return room;
	}

}

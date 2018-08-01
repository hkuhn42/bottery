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
/**
 * 
 */
package rocks.bottery.connector;

import com.pengrad.telegrambot.model.User;

import rocks.bottery.bot.IParticipant;

/**
 * Participant for the telegram connector
 * 
 * {@link #getConnectorParticipant()} gives access to the com.pengrad.telegrambot.model.User
 * 
 * @author Harald Kuhn
 *
 */
public class TelegramParticipant implements IParticipant {

	private User user;

	public TelegramParticipant(User user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IParticipant#getId()
	 */
	@Override
	public String getId() {
		return String.valueOf(user.id());
	}

	@Override
	public String getAddress() {
		return user.username();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IParticipant#getName()
	 */
	@Override
	public String getName() {
		return user.firstName() + " " + user.lastName();
	}

	@Override
	public String getChannel() {
		return "telegram";
	}

	@Override
	public Object getConnectorParticipant() {
		return user;
	}
}

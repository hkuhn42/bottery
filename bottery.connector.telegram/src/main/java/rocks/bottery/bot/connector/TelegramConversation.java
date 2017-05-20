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
package rocks.bottery.bot.connector;

import com.pengrad.telegrambot.model.Chat;

import rocks.bottery.bot.IConversation;

/**
 * @author Harald Kuhn
 *
 */
public class TelegramConversation implements IConversation {

	private Chat chat;

	TelegramConversation(Chat chat) {
		this.chat = chat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConversation#getId()
	 */
	@Override
	public String getId() {
		return String.valueOf(chat.id());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConversation#getChannel()
	 */
	@Override
	public String getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConversation#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		// TODO Auto-generated method stub
		return TelegramConnector.class.getName();
	}

}

/**
 *    Copyright (C) 2016-2017 Harald Kuhn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.bot.connector.telegram;

import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IIntent;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.GenericParticipant;

/**
 * @author Harald Kuhn
 *
 */
public class TelegramActivity implements IActivity {

	private Update			  update;
	private List<IAttachment> attachement;
	private IIntent<?>		  intent;

	TelegramActivity(Update update) {
		this.update = update;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getId()
	 */
	@Override
	public String getId() {
		if (update.message() != null) {
			return String.valueOf(update.message().messageId());
		}
		return String.valueOf(update.updateId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		throw new UnsupportedOperationException("id cannot be changed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getTopic()
	 */
	@Override
	public String getTopic() {
		if (update.message() != null) {
			return update.message().text();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setTopic(java.lang.String)
	 */
	@Override
	public void setTopic(String topic) {
		throw new UnsupportedOperationException("topic cannot be changed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getType()
	 */
	@Override
	public ActivityType getType() {
		if (update.message() != null) {
			return ActivityType.MESSAGE;
		}
		return ActivityType.OTHER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setType(org.sylvani.bot.ActivityType)
	 */
	@Override
	public void setType(ActivityType type) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return new TelegramParticipant(update.message().from());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setFrom(org.sylvani.bot.IParticipant)
	 */
	@Override
	public void setFrom(IParticipant from) {
		throw new UnsupportedOperationException("from cannot be changed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getRecipient()
	 */
	@Override
	public IParticipant getRecipient() {
		return new GenericParticipant("bot", "bot");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setRecipient(org.sylvani.bot.IParticipant)
	 */
	@Override
	public void setRecipient(IParticipant recipient) {
		throw new UnsupportedOperationException("recipient cannot be changed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getText()
	 */
	@Override
	public String getText() {
		if (update.message() != null) {
			return update.message().text();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getConversation()
	 */
	@Override
	public IConversation getConversation() {
		if (update.message() != null) {
			return new TelegramConversation(update.message().chat());
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setConversation(org.sylvani.bot.IConversation)
	 */
	@Override
	public void setConversation(IConversation conversation) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setAttachments(java.util.List)
	 */
	@Override
	public void setAttachments(List<IAttachment> attachement) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getAttachments()
	 */
	@Override
	public List<IAttachment> getAttachments() {
		if (attachement == null) {
			attachement = new ArrayList<>();
		}
		return attachement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getConnectorActivity()
	 */
	@Override
	public Object getConnectorActivity() {
		return update;
	}

	public Update getUpdate() {
		return update;
	}

	@Override
	public IIntent<?> getIntent() {
		return intent;
	}

	@Override
	public void setIntent(IIntent<?> intent) {
		this.intent = intent;
	}
}
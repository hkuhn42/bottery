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

import java.util.Collections;
import java.util.List;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericActivity;

/**
 * @author Harald Kuhn
 *
 */
public class GitterActivity extends GenericActivity implements IActivity {

	private static final long  serialVersionUID	= 1L;

	private Message			   message;
	private GitterParticipant  botUser;
	private GitterConversation room;

	protected GitterActivity(Message message, GitterParticipant botUser, GitterConversation room) {
		this.message = message;
		this.botUser = botUser;
		this.room = room;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getId()
	 */
	@Override
	public String getId() {
		return message.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getTopic()
	 */
	@Override
	public String getTopic() {
		return message.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setTopic(java.lang.String)
	 */
	@Override
	public void setTopic(String topic) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getType()
	 */
	@Override
	public ActivityType getType() {
		return ActivityType.MESSAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setType(rocks.bottery.bot.ActivityType)
	 */
	@Override
	public void setType(ActivityType type) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return new GitterParticipant(message.getFromUser());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setFrom(rocks.bottery.bot.IParticipant)
	 */
	@Override
	public void setFrom(IParticipant from) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getRecipient()
	 */
	@Override
	public IParticipant getRecipient() {
		return botUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setRecipient(rocks.bottery.bot.IParticipant)
	 */
	@Override
	public void setRecipient(IParticipant recipient) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getText()
	 */
	@Override
	public String getText() {
		return message.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConversation()
	 */
	@Override
	public IConversation getConversation() {
		return room;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setConversation(rocks.bottery.bot.IConversation)
	 */
	@Override
	public void setConversation(IConversation conversation) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setAttachments(java.util.List)
	 */
	@Override
	public void setAttachments(List<IAttachment> attachement) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getAttachments()
	 */
	@Override
	public List<IAttachment> getAttachments() {
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConnectorActivity()
	 */
	@Override
	public Object getConnectorActivity() {
		return message;
	}

}

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
package rocks.bottery.connector.line;

import java.util.List;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.ActivityBase;
import rocks.bottery.connector.GenericConversation;

/**
 * A line based activity implementation
 * 
 * @author Harald Kuhn
 */
public class LineActivity extends ActivityBase {

	private static final long serialVersionUID = 1L;

	private Event			  event;

	public LineActivity(Event event) {
		this.event = event;
		setAttribute(LineConnector.REPLY_TOKEN, event.getReplyToken());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getId()
	 */
	@Override
	public String getId() {
		if (event.getMessage() != null) {
			return event.getMessage().getId();
		}
		return String.valueOf(hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getTopic()
	 */
	@Override
	public String getTopic() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getType()
	 */
	@Override
	public ActivityType getType() {
		if ("message".equalsIgnoreCase(event.getType())) {
			return ActivityType.MESSAGE;
		}
		return ActivityType.OTHER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return new LineParticipant(event.getSource());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getRecipient()
	 */
	@Override
	public IParticipant getRecipient() {
		return LineConnector.PARTICIPANT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getText()
	 */
	@Override
	public String getText() {
		if (event.getMessage() != null) {
			return event.getMessage().getText();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConversation()
	 */
	@Override
	public IConversation getConversation() {
		// FIXME: determine how to determine a better conversation id
		return new GenericConversation(event.getSource().getUserId(), "line", LineConnector.class.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getAttachments()
	 */
	@Override
	public List<IAttachment> getAttachments() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConnectorActivity()
	 */
	@Override
	public Object getConnectorActivity() {
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getChoices()
	 */
	@Override
	public List<Choice<?>> getChoices() {
		// TODO Auto-generated method stub
		return null;
	}

}

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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericActivity;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Activity impl. for Discord
 * 
 * @author Harald Kuhn
 */
public class DiscordActivity extends GenericActivity {

	private static final long serialVersionUID = 1L;

	private IMessage		  message;

	public DiscordActivity(IMessage message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getId()
	 */
	@Override
	public String getId() {
		return message.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getTopic()
	 */
	@Override
	public String getTopic() {
		return message.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getType()
	 */
	@Override
	public ActivityType getType() {
		ActivityType type = null;
		// TODO map types
		switch (message.getType()) {
			case DEFAULT:
				type = ActivityType.MESSAGE;
				break;
			default:
				type = ActivityType.OTHER;
				break;
		}
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return new DiscordParticipant(message.getAuthor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getRecipient()
	 */
	@Override
	public IParticipant getRecipient() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getText()
	 */
	@Override
	public String getText() {
		return message.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConversation()
	 */
	@Override
	public IConversation getConversation() {
		return new DiscordConversation(message.getChannel(), "discord");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getAttachments()
	 */
	@Override
	public List<IAttachment> getAttachments() {
		if (message.getAttachments() != null && message.getAttachments().size() > 0) {
			return message.getAttachments().stream().map(obj -> new DiscordAttachment(obj)).collect(Collectors.toList());
		}
		return Collections.emptyList(); // message.getAttachments();
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

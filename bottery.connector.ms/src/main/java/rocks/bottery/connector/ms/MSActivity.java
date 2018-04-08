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
package rocks.bottery.connector.ms;

import java.util.ArrayList;
import java.util.List;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.connector.GenericActivity;
import rocks.bottery.connector.ms.model.Activity;
import rocks.bottery.connector.ms.model.Attachment;
import rocks.bottery.connector.ms.model.ChannelAccount;

/**
 * Wrapper around the BotFramework activity
 * 
 * @author Harald Kuhn
 */
public class MSActivity extends GenericActivity implements IActivity {

	private static final long serialVersionUID = 1L;

	private IIntent			  intent;
	private Activity		  activity;

	MSActivity(Activity activity) {
		this.activity = activity;
	}

	Activity getActivity() {
		return activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getId()
	 */
	@Override
	public String getId() {
		return activity.getId();
	}

	public void setId(String id) {
		activity.setId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getTopic()
	 */
	@Override
	public String getTopic() {
		return activity.getTopicName();
	}

	public void setTopic(String topic) {
		activity.setTopicName(topic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getType()
	 */
	@Override
	public ActivityType getType() {
		String type = activity.getType();
		if ("message".equalsIgnoreCase(type)) {
			return ActivityType.MESSAGE;
		}
		else if ("contactRelationUpdate".equalsIgnoreCase(type) && "add".equalsIgnoreCase(activity.getAction())) {
			return ActivityType.NEW_CONTACT;
		}
		else if ("typing".equalsIgnoreCase(type)) {
			return ActivityType.OTHER;
		}
		return ActivityType.OTHER;
	}

	public void setType(ActivityType type) {
		switch (type) {
			case MESSAGE:
				activity.setType("message");
				break;
			default:
				break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return new MSParticipant(activity.getFrom());
	}

	public void setFrom(IParticipant from) {
		if (from instanceof MSParticipant) {
			activity.setFrom(((MSParticipant) from).getChannelAccount());
		}
		else {
			ChannelAccount account = new ChannelAccount();
			account.setId(from.getId());
			account.setName(from.getName());
			activity.setFrom(account);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#getRecipients()
	 */
	@Override
	public IParticipant getRecipient() {
		return new MSParticipant(activity.getRecipient());
	}

	public void setRecipient(IParticipant recipient) {
		activity.setRecipient(((MSParticipant) recipient).getChannelAccount());
	}

	@Override
	public String getText() {
		return activity.getText();
	}

	public void setText(String text) {
		activity.setText(text);
	}

	@Override
	public IConversation getConversation() {
		return new MSConversation(activity.getConversation(), activity.getChannelId());
	}

	public void setAttachments(List<IAttachment> attachments) {
		List<Attachment> msAttachments = new ArrayList<>();
		for (IAttachment attachment : attachments) {

		}
		activity.setAttachments(msAttachments);
	}

	@Override
	public List<IAttachment> getAttachments() {
		List<Attachment> attas = activity.getAttachments();
		List<IAttachment> attachments = new ArrayList<>();
		for (Attachment atta : attas) {

		}
		return attachments;
	}

	@Override
	public Object getConnectorActivity() {
		return activity;
	}

	@Override
	public IIntent getIntent() {
		return intent;
	}

	@Override
	public void setIntent(IIntent intent) {
		this.intent = intent;
	}

	@Override
	public List<Choice<?>> getChoices() {
		return new ArrayList<>();
	}
}

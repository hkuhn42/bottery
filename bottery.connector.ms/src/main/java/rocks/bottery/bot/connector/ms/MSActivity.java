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
package rocks.bottery.bot.connector.ms;

import java.util.ArrayList;
import java.util.List;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IIntent;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.ms.model.Activity;
import rocks.bottery.bot.connector.ms.model.Attachment;
import rocks.bottery.bot.connector.ms.model.ChannelAccount;

/**
 * @author Harald Kuhn
 *
 */
public class MSActivity implements IActivity {

	private IIntent<?> intent;
	private Activity   activity;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setId(java.lang.String)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setTopic(java.lang.String)
	 */
	@Override
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
		else if ("typing".equalsIgnoreCase(type)) {
			return ActivityType.OTHER;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setFrom(org.sylvani.bot.IParticipant)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivity#setRecipients(java.util.List)
	 */
	@Override
	public void setRecipient(IParticipant recipient) {
		activity.setRecipient(((MSParticipant) recipient).getChannelAccount());
	}

	@Override
	public String getText() {
		return activity.getText();
	}

	@Override
	public void setText(String text) {
		activity.setText(text);
	}

	@Override
	public IConversation getConversation() {
		return new MSConversation(activity.getConversation(), activity.getChannelId());
	}

	@Override
	public void setConversation(IConversation conversation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAttachments(List<IAttachment> attachement) {

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
	public IIntent<?> getIntent() {
		return intent;
	}

	@Override
	public void setIntent(IIntent<?> intent) {
		this.intent = intent;
	}

	@Override
	public void setChoices(List<Choice<?>> choices) {

	}

	@Override
	public List<Choice<?>> getChoices() {
		return null;
	}
}

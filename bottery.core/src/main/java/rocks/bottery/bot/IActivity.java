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
package rocks.bottery.bot;

import java.util.List;

import rocks.bottery.bot.recognizers.IIntent;

/**
 * Defines an activity in a chat
 * 
 * Activities are of a type (like message, a state change or anything else)
 * 
 * Most connectors will implement their own Activity as a wrapper around the connector message / activity object
 * 
 * {@link #getConnectorActivity()} exposes the original object with must be cast to the corresponding type
 * 
 * Most fields are optional and dependant on the backing system
 * 
 * @author Harald Kuhn
 *
 */
public interface IActivity {

	/**
	 * the id of the activity or null if no id is available
	 * 
	 * @return
	 */
	String getId();

	/**
	 * get the Topic of the activity or null if no topic is present or supported
	 * 
	 * @return topic string or null
	 */
	String getTopic();

	/**
	 * the type of the activity
	 * 
	 * @return
	 */
	ActivityType getType();

	/**
	 * the sending participant
	 * 
	 * @return
	 */
	IParticipant getFrom();

	/**
	 * the receiving participant (in most cases, this would be the bot account)
	 * 
	 * @return
	 */
	IParticipant getRecipient();

	/**
	 * the text of the activity or null if none is present
	 * 
	 * @return
	 */
	public String getText();

	/**
	 * the conversation (aka the chat or session of the connector)
	 * 
	 * @return
	 */
	public IConversation getConversation();

	/**
	 * Get a list of attachments, can be empty, should not return null
	 * 
	 * @return
	 */
	public List<IAttachment> getAttachments();

	/**
	 * Get the connector (can be used to access connector specific features)
	 * 
	 * @return
	 */
	public Object getConnectorActivity();

	/**
	 * get the Intent the recognizer detected (may be null)
	 * 
	 * @return
	 */
	public IIntent getIntent();

	/**
	 * set the intent of this activity, normally this is called by the bot with an intent provided by the recognizer
	 * 
	 * @param intent
	 */
	public void setIntent(IIntent intent);

	/**
	 * a list of choices to be presented to the user the actual presentation is dependent of the technological
	 * 
	 * @return possiblities for a choice
	 */
	public List<Choice<?>> getChoices();

}
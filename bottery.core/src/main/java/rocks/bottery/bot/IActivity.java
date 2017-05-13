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
package rocks.bottery.bot;

import java.util.List;

/**
 * Defines an activity in a chat
 * 
 * Activities are of a type (like message, a state change or anything else)
 * 
 * @author Harald Kuhn
 *
 */
public interface IActivity {

	String getId();

	void setId(String id);

	String getTopic();

	void setTopic(String topic);

	ActivityType getType();

	void setType(ActivityType type);

	IParticipant getFrom();

	void setFrom(IParticipant from);

	IParticipant getRecipient();

	void setRecipient(IParticipant recipient);

	public String getText();

	public void setText(String text);

	public IConversation getConversation();

	public void setConversation(IConversation conversation);

	public void setAttachments(List<IAttachment> attachement);

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
	public IIntent<?> getIntent();

	public void setIntent(IIntent<?> intent);
}
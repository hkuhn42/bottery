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
package rocks.bottery.connector.generic;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A generic activity
 * 
 * @author hkuhn
 */
@XmlRootElement(name = "activity")
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String			  id;
	private String			  topic;
	private Date			  date;
	private ActivityType	  type;
	private Participant		  from;
	private Participant		  to;
	private String			  text;
	private Conversation	  conversation;
	private Locale			  locale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public Participant getFrom() {
		return from;
	}

	public void setFrom(Participant from) {
		this.from = from;
	}

	public Participant getTo() {
		return to;
	}

	public void setTo(Participant to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

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
package rocks.bottery.connector.stride.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Generated class for the JSON root object of a message or mention
 * 
 * @author Harald Kuhn
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cloudId", "message", "recipients", "sender", "conversation", "type" })
public class Activity implements Serializable {

	@JsonProperty("cloudId")
	private String				cloudId;
	@JsonProperty("message")
	private Message				message;
	@JsonProperty("recipients")
	private List<String>		recipients			 = new ArrayList<String>();
	@JsonProperty("sender")
	private Participant			sender;
	@JsonProperty("conversation")
	private Conversation		conversation;
	@JsonProperty("type")
	private String				type;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = 6420240227569633956L;

	@JsonProperty("cloudId")
	public String getCloudId() {
		return cloudId;
	}

	@JsonProperty("cloudId")
	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

	@JsonProperty("message")
	public Message getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(Message message) {
		this.message = message;
	}

	@JsonProperty("recipients")
	public List<String> getRecipients() {
		return recipients;
	}

	@JsonProperty("recipients")
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	@JsonProperty("sender")
	public Participant getSender() {
		return sender;
	}

	@JsonProperty("sender")
	public void setSender(Participant sender) {
		this.sender = sender;
	}

	@JsonProperty("conversation")
	public Conversation getConversation() {
		return conversation;
	}

	@JsonProperty("conversation")
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

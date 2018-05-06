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
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Generated class
 * 
 * @author Harald Kuhn
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "avatarUrl", "id", "isArchived", "name", "privacy", "topic", "type", "modified", "created" })
public class Conversation implements Serializable {

	@JsonProperty("avatarUrl")
	private String				avatarUrl;
	@JsonProperty("id")
	private String				id;
	@JsonProperty("isArchived")
	private boolean				isArchived;
	@JsonProperty("name")
	private String				name;
	@JsonProperty("privacy")
	private String				privacy;
	@JsonProperty("topic")
	private String				topic;
	@JsonProperty("type")
	private String				type;
	@JsonProperty("modified")
	private String				modified;
	@JsonProperty("created")
	private String				created;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -887482071073499283L;

	@JsonProperty("avatarUrl")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@JsonProperty("avatarUrl")
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("isArchived")
	public boolean isIsArchived() {
		return isArchived;
	}

	@JsonProperty("isArchived")
	public void setIsArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("privacy")
	public String getPrivacy() {
		return privacy;
	}

	@JsonProperty("privacy")
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	@JsonProperty("topic")
	public String getTopic() {
		return topic;
	}

	@JsonProperty("topic")
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("modified")
	public String getModified() {
		return modified;
	}

	@JsonProperty("modified")
	public void setModified(String modified) {
		this.modified = modified;
	}

	@JsonProperty("created")
	public String getCreated() {
		return created;
	}

	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
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

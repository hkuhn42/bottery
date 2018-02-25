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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "id", "name", "topic", "avatarUrl", "uri", "oneToOne", "userCount", "unreadItems", "mentions", "lastAccessTime", "lurk", "url",
        "githubType", "security", "noindex", "tags", "roomMember", "groupId", "public" })
public class Room implements Serializable {

	@JsonProperty("id")
	private String				id;
	@JsonProperty("name")
	private String				name;
	@JsonProperty("topic")
	private String				topic;
	@JsonProperty("avatarUrl")
	private String				avatarUrl;
	@JsonProperty("uri")
	private String				uri;
	@JsonProperty("oneToOne")
	private boolean				oneToOne;
	@JsonProperty("userCount")
	private int					userCount;
	@JsonProperty("unreadItems")
	private int					unreadItems;
	@JsonProperty("mentions")
	private int					mentions;
	@JsonProperty("lastAccessTime")
	private String				lastAccessTime;
	@JsonProperty("lurk")
	private boolean				lurk;
	@JsonProperty("url")
	private String				url;
	@JsonProperty("githubType")
	private String				githubType;
	@JsonProperty("security")
	private String				security;
	@JsonProperty("noindex")
	private boolean				noindex;
	@JsonProperty("tags")
	private List<Object>		tags				 = new ArrayList<Object>();
	@JsonProperty("roomMember")
	private boolean				roomMember;
	@JsonProperty("groupId")
	private String				groupId;
	@JsonProperty("public")
	private boolean				_public;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = 5447066325117414446L;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("topic")
	public String getTopic() {
		return topic;
	}

	@JsonProperty("topic")
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@JsonProperty("avatarUrl")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@JsonProperty("avatarUrl")
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@JsonProperty("uri")
	public String getUri() {
		return uri;
	}

	@JsonProperty("uri")
	public void setUri(String uri) {
		this.uri = uri;
	}

	@JsonProperty("oneToOne")
	public boolean isOneToOne() {
		return oneToOne;
	}

	@JsonProperty("oneToOne")
	public void setOneToOne(boolean oneToOne) {
		this.oneToOne = oneToOne;
	}

	@JsonProperty("userCount")
	public int getUserCount() {
		return userCount;
	}

	@JsonProperty("userCount")
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	@JsonProperty("unreadItems")
	public int getUnreadItems() {
		return unreadItems;
	}

	@JsonProperty("unreadItems")
	public void setUnreadItems(int unreadItems) {
		this.unreadItems = unreadItems;
	}

	@JsonProperty("mentions")
	public int getMentions() {
		return mentions;
	}

	@JsonProperty("mentions")
	public void setMentions(int mentions) {
		this.mentions = mentions;
	}

	@JsonProperty("lastAccessTime")
	public String getLastAccessTime() {
		return lastAccessTime;
	}

	@JsonProperty("lastAccessTime")
	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	@JsonProperty("lurk")
	public boolean isLurk() {
		return lurk;
	}

	@JsonProperty("lurk")
	public void setLurk(boolean lurk) {
		this.lurk = lurk;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("githubType")
	public String getGithubType() {
		return githubType;
	}

	@JsonProperty("githubType")
	public void setGithubType(String githubType) {
		this.githubType = githubType;
	}

	@JsonProperty("security")
	public String getSecurity() {
		return security;
	}

	@JsonProperty("security")
	public void setSecurity(String security) {
		this.security = security;
	}

	@JsonProperty("noindex")
	public boolean isNoindex() {
		return noindex;
	}

	@JsonProperty("noindex")
	public void setNoindex(boolean noindex) {
		this.noindex = noindex;
	}

	@JsonProperty("tags")
	public List<Object> getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(List<Object> tags) {
		this.tags = tags;
	}

	@JsonProperty("roomMember")
	public boolean isRoomMember() {
		return roomMember;
	}

	@JsonProperty("roomMember")
	public void setRoomMember(boolean roomMember) {
		this.roomMember = roomMember;
	}

	@JsonProperty("groupId")
	public String getGroupId() {
		return groupId;
	}

	@JsonProperty("groupId")
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@JsonProperty("public")
	public boolean isPublic() {
		return _public;
	}

	@JsonProperty("public")
	public void setPublic(boolean _public) {
		this._public = _public;
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

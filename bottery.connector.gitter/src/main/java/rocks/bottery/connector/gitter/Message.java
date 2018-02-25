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
@JsonPropertyOrder({ "id", "text", "html", "sent", "fromUser", "unread", "readBy", "urls", "mentions", "issues", "meta", "v" })
public class Message implements Serializable {

	@JsonProperty("id")
	private String				id;
	@JsonProperty("text")
	private String				text;
	@JsonProperty("html")
	private String				html;
	@JsonProperty("sent")
	private String				sent;
	@JsonProperty("fromUser")
	private User				fromUser;
	@JsonProperty("unread")
	private boolean				unread;
	@JsonProperty("readBy")
	private int					readBy;
	@JsonProperty("urls")
	private List<Object>		urls				 = new ArrayList<Object>();
	@JsonProperty("mentions")
	private List<Object>		mentions			 = new ArrayList<Object>();
	@JsonProperty("issues")
	private List<Object>		issues				 = new ArrayList<Object>();
	@JsonProperty("meta")
	private List<Object>		meta				 = new ArrayList<Object>();
	@JsonProperty("v")
	private int					v;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -7623537128508477959L;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("html")
	public String getHtml() {
		return html;
	}

	@JsonProperty("html")
	public void setHtml(String html) {
		this.html = html;
	}

	@JsonProperty("sent")
	public String getSent() {
		return sent;
	}

	@JsonProperty("sent")
	public void setSent(String sent) {
		this.sent = sent;
	}

	@JsonProperty("fromUser")
	public User getFromUser() {
		return fromUser;
	}

	@JsonProperty("fromUser")
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	@JsonProperty("unread")
	public boolean isUnread() {
		return unread;
	}

	@JsonProperty("unread")
	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	@JsonProperty("readBy")
	public int getReadBy() {
		return readBy;
	}

	@JsonProperty("readBy")
	public void setReadBy(int readBy) {
		this.readBy = readBy;
	}

	@JsonProperty("urls")
	public List<Object> getUrls() {
		return urls;
	}

	@JsonProperty("urls")
	public void setUrls(List<Object> urls) {
		this.urls = urls;
	}

	@JsonProperty("mentions")
	public List<Object> getMentions() {
		return mentions;
	}

	@JsonProperty("mentions")
	public void setMentions(List<Object> mentions) {
		this.mentions = mentions;
	}

	@JsonProperty("issues")
	public List<Object> getIssues() {
		return issues;
	}

	@JsonProperty("issues")
	public void setIssues(List<Object> issues) {
		this.issues = issues;
	}

	@JsonProperty("meta")
	public List<Object> getMeta() {
		return meta;
	}

	@JsonProperty("meta")
	public void setMeta(List<Object> meta) {
		this.meta = meta;
	}

	@JsonProperty("v")
	public int getV() {
		return v;
	}

	@JsonProperty("v")
	public void setV(int v) {
		this.v = v;
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

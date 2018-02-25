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
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "id", "username", "displayName", "url", "avatarUrl", "avatarUrlSmall", "avatarUrlMedium", "v", "gv" })
public class User implements Serializable {

	@JsonProperty("id")
	private String				id;
	@JsonProperty("username")
	private String				username;
	@JsonProperty("displayName")
	private String				displayName;
	@JsonProperty("url")
	private String				url;
	@JsonProperty("avatarUrl")
	private String				avatarUrl;
	@JsonProperty("avatarUrlSmall")
	private String				avatarUrlSmall;
	@JsonProperty("avatarUrlMedium")
	private String				avatarUrlMedium;
	@JsonProperty("v")
	private int					v;
	@JsonProperty("gv")
	private String				gv;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -8008673873876080732L;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty("displayName")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("avatarUrl")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@JsonProperty("avatarUrl")
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@JsonProperty("avatarUrlSmall")
	public String getAvatarUrlSmall() {
		return avatarUrlSmall;
	}

	@JsonProperty("avatarUrlSmall")
	public void setAvatarUrlSmall(String avatarUrlSmall) {
		this.avatarUrlSmall = avatarUrlSmall;
	}

	@JsonProperty("avatarUrlMedium")
	public String getAvatarUrlMedium() {
		return avatarUrlMedium;
	}

	@JsonProperty("avatarUrlMedium")
	public void setAvatarUrlMedium(String avatarUrlMedium) {
		this.avatarUrlMedium = avatarUrlMedium;
	}

	@JsonProperty("v")
	public int getV() {
		return v;
	}

	@JsonProperty("v")
	public void setV(int v) {
		this.v = v;
	}

	@JsonProperty("gv")
	public String getGv() {
		return gv;
	}

	@JsonProperty("gv")
	public void setGv(String gv) {
		this.gv = gv;
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

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
package rocks.bottery.bot.recognizer.luis;

import java.io.Serializable;
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
@JsonPropertyOrder({ "query", "topScoringIntent", "entities" })
public class LuisResponse implements Serializable {

	@JsonProperty("query")
	private String				query;
	@JsonProperty("topScoringIntent")
	private TopScoringIntent	topScoringIntent;
	@JsonProperty("entities")
	private List<Entity>		entities			 = null;
	@JsonIgnore

	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -6799864028859307331L;

	@JsonProperty("query")
	public String getQuery() {
		return query;
	}

	@JsonProperty("query")
	public void setQuery(String query) {
		this.query = query;
	}

	@JsonProperty("topScoringIntent")
	public TopScoringIntent getTopScoringIntent() {
		return topScoringIntent;
	}

	@JsonProperty("topScoringIntent")
	public void setTopScoringIntent(TopScoringIntent topScoringIntent) {
		this.topScoringIntent = topScoringIntent;
	}

	@JsonProperty("entities")
	public List<Entity> getEntities() {
		return entities;
	}

	@JsonProperty("entities")
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return getTopScoringIntent() + ":" + super.toString();
	}
}

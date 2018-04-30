
package rocks.bottery.bot.recognizer.witai;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entities implements Serializable {

	@JsonProperty("datetime")
	private List<Datetime>		datetime			 = new ArrayList<Datetime>();
	@JsonProperty("location")
	private List<Location>		location			 = new ArrayList<Location>();

	@JsonProperty("pizza_type")
	private List<Entity>		entities			 = new ArrayList<Entity>();

	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = 6697741441390742375L;

	@JsonProperty("datetime")
	public List<Datetime> getDatetime() {
		return datetime;
	}

	@JsonProperty("datetime")
	public void setDatetime(List<Datetime> datetime) {
		this.datetime = datetime;
	}

	@JsonProperty("location")
	public List<Location> getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(List<Location> location) {
		this.location = location;
	}

	public List<Entity> getEntities() {
		return entities;
	}

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

}

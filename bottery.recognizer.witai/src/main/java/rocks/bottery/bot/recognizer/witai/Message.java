
package rocks.bottery.bot.recognizer.witai;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "id", "sourceId", "destinationId", "type", "body", "v" })
public class Message implements Serializable {

	@JsonProperty("id")
	private String				id;
	@JsonProperty("sourceId")
	private String				sourceId;
	@JsonProperty("destinationId")
	private String				destinationId;
	@JsonProperty("type")
	private String				type;
	@JsonProperty("body")
	private String				body;
	@JsonProperty("v")
	private int					v;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = 7980016900744592507L;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("sourceId")
	public String getSourceId() {
		return sourceId;
	}

	@JsonProperty("sourceId")
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@JsonProperty("destinationId")
	public String getDestinationId() {
		return destinationId;
	}

	@JsonProperty("destinationId")
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("body")
	public String getBody() {
		return body;
	}

	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
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

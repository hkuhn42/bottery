
package rocks.bottery.bot.recognizer.witai;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "confidence", "intent", "_text", "entities" })
public class Outcome implements Serializable {

	@JsonProperty("confidence")
	private double				confidence;
	@JsonProperty("intent")
	private String				intent;
	@JsonProperty("_text")
	private String				text;
	@JsonProperty("entities")
	private Entities			entities;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -4286849664786736240L;

	@JsonProperty("confidence")
	public double getConfidence() {
		return confidence;
	}

	@JsonProperty("confidence")
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	@JsonProperty("intent")
	public String getIntent() {
		return intent;
	}

	@JsonProperty("intent")
	public void setIntent(String intent) {
		this.intent = intent;
	}

	@JsonProperty("_text")
	public String getText() {
		return text;
	}

	@JsonProperty("_text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("entities")
	public Entities getEntities() {
		return entities;
	}

	@JsonProperty("entities")
	public void setEntities(Entities entities) {
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

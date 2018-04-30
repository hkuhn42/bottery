
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
public class Datetime extends Entity implements Serializable {

	@JsonProperty("values")
	private List<Value>			values				 = new ArrayList<Value>();
	@JsonProperty("grain")
	private String				grain;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = 8670612521705005414L;

	@JsonProperty("values")
	public List<Value> getValues() {
		return values;
	}

	@JsonProperty("values")
	public void setValues(List<Value> values) {
		this.values = values;
	}

	@JsonProperty("grain")
	public String getGrain() {
		return grain;
	}

	@JsonProperty("grain")
	public void setGrain(String grain) {
		this.grain = grain;
	}

	@Override
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
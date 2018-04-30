
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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_text", "outcomes", "msg_id" })
public class Response implements Serializable {

	@JsonProperty("_text")
	private String				text;
	@JsonProperty("outcomes")
	private List<Outcome>		outcomes			 = new ArrayList<Outcome>();
	@JsonProperty("msg_id")
	private String				msgId;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = 7004171143545998461L;

	@JsonProperty("_text")
	public String getText() {
		return text;
	}

	@JsonProperty("_text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("outcomes")
	public List<Outcome> getOutcomes() {
		if (outcomes == null) {
			outcomes = new ArrayList<>();
		}
		return outcomes;
	}

	@JsonProperty("outcomes")
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	@JsonProperty("msg_id")
	public String getMsgId() {
		return msgId;
	}

	@JsonProperty("msg_id")
	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

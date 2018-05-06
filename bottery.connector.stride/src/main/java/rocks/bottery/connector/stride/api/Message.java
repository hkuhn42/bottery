
package rocks.bottery.connector.stride.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "body", "text", "sender", "ts" })
@XmlRootElement(name = "Message")
public class Message implements Serializable {

	@JsonProperty("id")
	private String				id;
	@JsonProperty("body")
	private Body				body;
	@JsonProperty("text")
	private String				text;
	@JsonProperty("sender")
	private Participant			sender;
	@JsonProperty("ts")
	private String				ts;
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -4942594968160423247L;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("body")
	public Body getBody() {
		if (body == null) {
			body = new Body();
		}
		return body;
	}

	@JsonProperty("body")
	public void setBody(Body body) {
		this.body = body;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("sender")
	public Participant getSender() {
		return sender;
	}

	@JsonProperty("sender")
	public void setSender(Participant sender) {
		this.sender = sender;
	}

	@JsonProperty("ts")
	public String getTs() {
		return ts;
	}

	@JsonProperty("ts")
	public void setTs(String ts) {
		this.ts = ts;
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

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
package rocks.bottery.bot.recognizer.luis.qna;

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
@JsonPropertyOrder({ "questions", "answer", "score", "id", "source", "metadata" })
public class Answer implements Serializable {

	@JsonProperty("questions")
	private List<String>		questions			 = new ArrayList<String>();
	@JsonProperty("answer")
	private String				answer;
	@JsonProperty("score")
	private double				score;
	@JsonProperty("id")
	private int					id;
	@JsonProperty("source")
	private String				source;
	@JsonProperty("metadata")
	private List<Object>		metadata			 = new ArrayList<Object>();
	@JsonIgnore
	private Map<String, Object>	additionalProperties = new HashMap<String, Object>();
	private final static long	serialVersionUID	 = -13809527033921439L;

	@JsonProperty("questions")
	public List<String> getQuestions() {
		return questions;
	}

	@JsonProperty("questions")
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	@JsonProperty("answer")
	public String getAnswer() {
		return answer;
	}

	@JsonProperty("answer")
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@JsonProperty("score")
	public double getScore() {
		return score;
	}

	@JsonProperty("score")
	public void setScore(double score) {
		this.score = score;
	}

	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("source")
	public String getSource() {
		return source;
	}

	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("metadata")
	public List<Object> getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(List<Object> metadata) {
		this.metadata = metadata;
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

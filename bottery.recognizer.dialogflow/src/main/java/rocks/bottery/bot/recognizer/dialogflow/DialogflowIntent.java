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
package rocks.bottery.bot.recognizer.dialogflow;

import java.util.Map;

import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.protobuf.Value;

import rocks.bottery.bot.recognizers.CommandIntent;
import rocks.bottery.bot.recognizers.IIntent;

/**
 * An {@link IIntent} implementation for Dialogflow
 * 
 * @author Harald Kuhn
 */
public class DialogflowIntent extends CommandIntent {

	private static final long serialVersionUID = 1L;

	private QueryResult		  queryResult;

	public DialogflowIntent(String intent, QueryResult queryResult) {
		super(intent);
		this.queryResult = queryResult;

		Map<String, Value> params = queryResult.getParameters().getFieldsMap();
		for (Map.Entry<String, Value> entry : params.entrySet()) {
			// FIXME: map types
			setAttribute(entry.getKey(), entry.getValue().getStringValue());
		}
	}

	@Override
	public Object getRecognizerIntent() {
		return queryResult;
	}

	@Override
	public String getResponseSuggestion() {
		return queryResult.getFulfillmentText();
	}

	@Override
	public double getConfidence() {
		return queryResult.getIntentDetectionConfidence();
	}
}

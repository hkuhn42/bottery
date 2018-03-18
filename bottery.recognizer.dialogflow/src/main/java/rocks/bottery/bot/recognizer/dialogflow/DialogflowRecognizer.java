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

import java.io.IOException;

import com.google.cloud.dialogflow.v2beta1.DetectIntentResponse;
import com.google.cloud.dialogflow.v2beta1.QueryInput;
import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.cloud.dialogflow.v2beta1.SessionName;
import com.google.cloud.dialogflow.v2beta1.SessionsClient;
import com.google.cloud.dialogflow.v2beta1.TextInput;
import com.google.cloud.dialogflow.v2beta1.TextInput.Builder;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.bot.recognizers.RecognizerBase;

/**
 * A recognizer using googles dialogflow api for recognizing the intent of an activity
 * 
 * @author Harald Kuhn
 */
public class DialogflowRecognizer extends RecognizerBase {

	public static final String NOTHING_RECOGIZED_INTENT	= "Default Fallback Intent";

	private String			   projectId				= "bottery-weather";
	private String			   languageCode				= "de-DE";

	public DialogflowRecognizer(String dialogFlowProjectId) {
		projectId = dialogFlowProjectId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IRecognizer#recognize(rocks.bottery.bot.ISession, rocks.bottery.bot.IActivity)
	 */
	@Override
	public IIntent recognize(ISession session, IActivity activity) {

		try (SessionsClient sessionsClient = SessionsClient.create()) {
			// Set the session name using the sessionId (UUID) and projectID (my-project-id)
			SessionName sessionName = SessionName.of(projectId, session.getId());

			Builder textInput = TextInput.newBuilder().setText(activity.getText()).setLanguageCode(languageCode);

			// Build the query with the TextInput
			QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

			// Performs the detect intent request
			DetectIntentResponse response = sessionsClient.detectIntent(sessionName, queryInput);

			// Display the query result
			QueryResult queryResult = response.getQueryResult();
			String intentName = mapIntentName(queryResult.getIntent().getDisplayName());

			return new DialogflowIntent(intentName, queryResult);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

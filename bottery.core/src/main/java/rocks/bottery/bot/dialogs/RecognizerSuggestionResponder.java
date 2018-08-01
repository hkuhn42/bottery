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
package rocks.bottery.bot.dialogs;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;

/**
 * A dialog which answers with the suggested response of the recognizer
 * 
 * This is usefull for cases where the answer is the fullfillment of an intent like in question and answer systems or
 * where the recognizer also delivers an answer
 * 
 * @author Harald Kuhn
 *
 */
public class RecognizerSuggestionResponder extends Utterance {

	public RecognizerSuggestionResponder(IModel<String> notSuggestionText) {
		super(notSuggestionText);
	}

	@Override
	public void handle(ISession session, IActivity request) {
		session.activeDialogFinished();
		super.handle(session, request);
	}

	@Override
	public IModel<String> getText(IActivity request, ISession session) {
		String suggestedResponse = null;
		if (request.getIntent() != null) {
			suggestedResponse = request.getIntent().getResponseSuggestion();
		}
		if (suggestedResponse != null) {
			return new Model<String>(suggestedResponse);
		}
		return super.getText(request, session);
	}

}

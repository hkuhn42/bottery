/**
 * Copyright (C) 2016-2017 Harald Kuhn
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
/**
 * 
 */
package rocks.bottery.bot.util;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.connector.ActivityBase;
import rocks.bottery.connector.GenericActivity;

/**
 * Utiltiy for creating activities to prompt text to the conversation
 * 
 * TODO: intergrate with bot and session
 * 
 * @author Harald Kuhn
 *
 */
public class Prompt {

	ISession session;

	public Prompt(ISession session) {
		this.session = session;
	}

	public static GenericActivity answer(IActivity inIActivity) {
		GenericActivity activity = new GenericActivity();

		activity.setId(inIActivity.getId() + "a");
		activity.setFrom(inIActivity.getRecipient());
		activity.setRecipient(inIActivity.getFrom());

		return activity;
	}

	public static ActivityBase answer(String text, IActivity inIActivity) {
		GenericActivity activity = answer(inIActivity);
		activity.setText(text);
		return activity;
	}

	public static ActivityBase choice(String text, String[] choices, IActivity inIActivity) {
		StringBuilder textBuilder = new StringBuilder(text);
		for (int i = 0; i < choices.length; i++) {
			textBuilder.append("\n" + (i + 1) + "." + choices[i]);
		}
		ActivityBase activity = answer(textBuilder.toString(), inIActivity);
		return activity;
	}

}

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
package rocks.bottery.bot.recognizers;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;

/**
 * Recognizer which returns the Intent of the activity. This is usefull for cases where the message system also detects
 * the intent (like Alexa)
 * 
 * @author Harald Kuhn
 *
 */
public class PreRecognizedIntentRecognizer extends RecognizerBase {

	@Override
	public IIntent recognize(ISession session, IActivity activity) {
		return activity.getIntent();
	}

}
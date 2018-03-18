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
package rocks.bottery.bot.recognizers;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;

/**
 * @author Harald Kuhn
 *
 */
public class CommandRecognizer extends RecognizerBase {

	public static String NO_INTENT = "none";

	private String[]	 pattern;

	public CommandRecognizer(String... pattern) {
		this.pattern = pattern;
		if (pattern == null) {
			throw new NullPointerException("pattern may not be null");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.recognize.IRecognizer#recognize(org.sylvani.bot.ISession, org.sylvani.bot.IActivity)
	 */
	@Override
	public IIntent recognize(ISession session, IActivity activity) {

		String text = activity.getText();
		for (String string : pattern) {
			if (text.contains(string)) {
				return new CommandIntent(mapIntentName(string));
			}
		}
		if (greedy) {
			return new CommandIntent(NO_INTENT);
		}
		return null;
	}

}

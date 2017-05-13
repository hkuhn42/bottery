/**
 *    Copyright (C) 2016-2017 Harald Kuhn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.bot.recognizers;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;

/**
 * Basic recongizer using java regexp
 * 
 * @author Harald Kuhn
 *
 */
public class RegexpRecognizer implements IRecognizer {

	private String pattern;

	public RegexpRecognizer(String pattern) {
		this.pattern = pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IRecognizer#match(org.sylvani.bot.connector.ms.model. Activity)
	 */
	@Override
	public double recognize(ISession session, IActivity activity) {
		if (activity.getText() != null && activity.getText().matches(getPattern())) {
			activity.setIntent(new CommandIntent(pattern));
			return 1.0;
		}
		return 0.0;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}

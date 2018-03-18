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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rocks.bottery.bot.IBotConfig;

/**
 * Base class for recognizers
 * 
 * @author Harald Kuhn
 *
 */
public abstract class RecognizerBase implements IRecognizer {

	protected boolean			greedy;
	private Map<String, String>	intentMapping;

	public RecognizerBase() {
		super();
	}

	/**
	 * Tries to map the intentname to another name from the Map. Retunrns the original intent if no mapping is found
	 * 
	 * Helpfull to map external intent names to internal ones (e.g. dialogflow or luis intents to the ones used in the
	 * dialogs of a bot)
	 * 
	 * @param intentName
	 * @return the mapped intent or the original intent
	 */
	protected String mapIntentName(String intentName) {
		// apply mapping if necessary
		if (intentMapping != null && intentMapping.get(intentName) != null) {
			intentName = getIntentMapping().get(intentName);
		}
		return intentName;
	}

	public boolean isGreedy() {
		return greedy;
	}

	public Map<String, String> getIntentMapping() {
		if (intentMapping == null) {
			intentMapping = new HashMap<>();
		}
		return intentMapping;
	}

	public void setIntentMapping(Map<String, String> intentMapping) {
		this.intentMapping = intentMapping;
	}

	@Override
	public void init(IBotConfig config) throws IOException {
		// nothing to setup
	}

}
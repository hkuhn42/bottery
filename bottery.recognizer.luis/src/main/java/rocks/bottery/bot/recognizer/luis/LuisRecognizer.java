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
package rocks.bottery.bot.recognizer.luis;

import java.io.IOException;

import org.apache.log4j.Logger;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.bot.recognizers.RecognizerBase;
import rocks.bottery.util.ClientFactory;

/**
 * Recognizer which uses the Microsoft Luis API to recognize intents
 * 
 * Can be configured as greedy (signals match for all results of api) or not greedy (dos not match for
 * {@value #NOTHING_RECOGNIZED_INTENT}
 * 
 * Supports mapping luis intents to bot intents (makes dialogs more portable)
 * 
 * @author Harald Kuhn
 */
public class LuisRecognizer extends RecognizerBase implements IRecognizer {

	public static final String SERVER_URL				 = "https://api.projectoxford.ai/luis/v2.0";

	/**
	 * the buildin intent for nothing recognized
	 */
	public static final String NOTHING_RECOGNIZED_INTENT = "builtin.intent.none";

	/**
	 * Create a new LuisRecognizer with greedy = true
	 */
	public LuisRecognizer() {
		this.greedy = true;
	}

	/**
	 * Create a new LuisRecognizer with given greedy flag
	 * 
	 * @param greedy
	 *            true for greedy false otherwise
	 */
	public LuisRecognizer(boolean greedy) {
		this.greedy = greedy;
	}

	private LuisApi	proxy;
	private String	appId;
	private String	subscriptionKey;

	@Override
	public void init(IBotConfig config) throws IOException {
		ClientFactory<LuisApi> factory = new ClientFactory<>(false);
		proxy = factory.prepareClientProxy(SERVER_URL, LuisApi.class);

		// proxy = initConversationRestProxy(SERVER_URL);
		appId = config.getSetting("luis.appId");
		subscriptionKey = config.getSetting("luis.subscriptionKey");
	}

	@Override
	public IIntent recognize(ISession session, IActivity activity) {
		try {
			LuisResponse intent = proxy.query(appId, subscriptionKey, activity.getText());
			Logger.getLogger(LuisRecognizer.class).debug(intent + "received");

			// if greedy is set or an intent recognized -> handle
			if (greedy || !NOTHING_RECOGNIZED_INTENT.equalsIgnoreCase(intent.getTopScoringIntent().getIntent())) {
				// get intent name
				String intentName = intent.getTopScoringIntent().getIntent();
				intentName = mapIntentName(intentName);
				// set intent
				return new LuisIntent(intentName, intent);
			}
			return null;
		}
		catch (Exception e) { // FIXME: improve exception handling
			e.printStackTrace();
			return null;
		}
	}
}

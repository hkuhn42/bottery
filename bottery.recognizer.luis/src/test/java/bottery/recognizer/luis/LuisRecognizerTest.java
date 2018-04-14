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
package bottery.recognizer.luis;

import org.junit.Assert;
import org.junit.Test;

import rocks.bottery.bot.recognizer.luis.Entity;
import rocks.bottery.bot.recognizer.luis.LuisApi;
import rocks.bottery.bot.recognizer.luis.LuisRecognizer;
import rocks.bottery.bot.recognizer.luis.LuisResponse;

/**
 * Basic api lvel unit test, does abort if not provided with appId and subKey without failing (would not be feasible to
 * run this test in travisCI)
 * 
 * @author Harald Kuhn
 */
public class LuisRecognizerTest extends LuisRecognizer {

	@Test
	public void testApiImp() {
		LuisApi proxy = LuisRecognizer.initConversationRestProxy(LuisRecognizer.SERVER_URL);
		String appId = System.getProperty("appId");
		String key = System.getProperty("subKey");

		if (appId == null || key == null) {
			System.out.println("test not set up correctly, aborting");
			return;
		}

		LuisResponse intent = proxy.query(appId, key, "delete peters special alarm at 23:00");
		System.out.println(intent.getTopScoringIntent().getIntent());
		for (Entity entity : intent.getEntities()) {
			System.out.println(entity.getEntity() + " " + entity.getType());
		}
		assert (intent != null);
		Assert.assertEquals(intent.getTopScoringIntent().getIntent(), "builtin.intent.alarm.delete_alarm");
	}

}

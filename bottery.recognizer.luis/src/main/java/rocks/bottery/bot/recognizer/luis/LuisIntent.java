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

import rocks.bottery.bot.recognizers.CommandIntent;

/**
 * Intent recognized by luis api
 * 
 * {@link #getRecognizerIntent()} returns the {@link LuisResponse} returned by the api
 * 
 * @author Harald Kuhn
 */
public class LuisIntent extends CommandIntent {

	private static final long serialVersionUID = 1L;

	private LuisResponse	  response;

	protected LuisIntent(String intentName, LuisResponse response) {
		super(intentName);
		this.response = response;

		for (Entity entity : response.getEntities()) {
			// FIXME: map types
			setAttribute(entity.getType(), entity.getEntity());
		}
	}

	@Override
	public Object getRecognizerIntent() {
		return response;
	}
}
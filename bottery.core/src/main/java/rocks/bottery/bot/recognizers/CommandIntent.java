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

import java.io.Serializable;

import rocks.bottery.bot.ContextBase;
import rocks.bottery.bot.IIntent;

/**
 * Basic intent for a command word
 * 
 * @author Harald Kuhn
 */
public class CommandIntent extends ContextBase implements IIntent, Serializable {

	private static final long serialVersionUID = 1L;

	private String			  intent;

	public CommandIntent(String intent) {
		this.intent = intent;
	}

	@Override
	public String getIntentName() {
		return intent;
	}

	@Override
	public Object getRecognizerIntent() {
		return intent;
	}
}
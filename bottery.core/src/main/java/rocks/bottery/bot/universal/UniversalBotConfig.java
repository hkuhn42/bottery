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
package rocks.bottery.bot.universal;

import java.io.IOException;

import rocks.bottery.bot.BotConfig;
import rocks.bottery.bot.IKnowThisIsWrongCrypt;
import rocks.bottery.bot.InMemoryActivityArchive;
import rocks.bottery.bot.InMemorySessionStore;
import rocks.bottery.bot.recognizers.CommandRecognizer;
import rocks.bottery.bot.resolver.mustache.MustacheVariableResolver;

/**
 * @author Harald Kuhn
 *
 */
public class UniversalBotConfig extends BotConfig {

	public UniversalBotConfig() {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("Bot.properties"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crypt = new IKnowThisIsWrongCrypt();
		recognizer = new CommandRecognizer();
		resolver = new MustacheVariableResolver();
		sessionStore = new InMemorySessionStore();
		archive = new InMemoryActivityArchive();
	}

}

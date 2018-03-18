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

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.ISession;

/**
 * Defines a recognizer for recognizing wether a dialog fits to the users intent
 * 
 * @author Harald Kuhn
 *
 */
public interface IRecognizer {

	/**
	 * init the recognizer
	 * 
	 * @param config
	 * @throws IOException
	 */
	public void init(IBotConfig config) throws IOException;

	/**
	 * 
	 * @param activity
	 * @return
	 */
	public IIntent recognize(ISession session, IActivity activity);

}

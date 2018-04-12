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
package rocks.bottery.messaging;

import rocks.bottery.bot.IActivity;
import rocks.bottery.connector.IConnector;

/**
 * Definition of a messaging receiver. Can be registered with an connector to receive calls for messages received
 * 
 * @author Harald Kuhn
 */
public interface IReceiver {

	/**
	 * Called for received activities
	 * 
	 * - tries to determine session or creates a new one
	 * 
	 * - delegates to
	 * 
	 * @param activity
	 *            the activity to handle
	 * @param connector
	 *            the connector the activity is from (and answers should be send to)
	 */
	void receive(IActivity activity, IConnector connector);

}
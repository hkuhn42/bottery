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
package rocks.bottery.bot;

import java.io.Serializable;

/**
 * Enum which defines ActivityTypes Not all types are supported by all connectors
 * 
 * @author Harald Kuhn
 *
 */
public enum ActivityType implements Serializable {

	/**
	 * other activity like status updates
	 */
	OTHER,

	/**
	 * an indicator that the user started typing
	 */
	TYPING_START,

	/**
	 * an indicator that the user ended typing
	 */
	TYPING_END,

	/**
	 * a message with text, attachements etc.
	 */
	MESSAGE,

	/**
	 * a new contact
	 */
	NEW_CONTACT,

	/**
	 * start a new conversation / chat
	 * 
	 * only used by Bot2BotConnector for now
	 */
	START,

}

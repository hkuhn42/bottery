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

import java.util.Properties;

import rocks.bottery.bot.ICrypt;

/**
 * Defines an interface for the configuration of messaging connectors
 * 
 * @author Harald Kuhn
 */
public interface IMessagingConfig {

	/**
	 * Get a string setting
	 * 
	 * @param name
	 *            the name of a setting
	 * @return the value of the setting or null
	 */
	public String getSetting(String name);
	
	/**
	 * Get all properties which keys have the given prefix. The prefix is removed from the keys. 
	 * 
	 * @param prefix the prefix
	 * @return all properties with the given prefix
	 */
	public Properties getSettings(String prefix);

	/**
	 * Get the crypt implementation used to encrypt / decrypt settings
	 * 
	 * @return the current crypt instance or null if none is set
	 */
	ICrypt getCrypt();

	/**
	 * Set the crypt implementation used to encrypt / decrypt settings
	 * 
	 * @param crypt
	 *            the crypt implementation to use
	 */
	void setCrypt(ICrypt crypt);
}

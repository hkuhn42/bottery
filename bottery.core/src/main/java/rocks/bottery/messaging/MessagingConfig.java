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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import rocks.bottery.bot.ICrypt;

/**
 * Default messaging config. If a settings starts with "Crypt " (note the blanc) it is considered to be encrypted with
 * the active ICrypt implementation and will be decrypted by getSetting before beeing returned
 * 
 * @author Harald Kuhn
 */
public class MessagingConfig implements IMessagingConfig {

	protected Map<String, String> properties;
	protected ICrypt	 crypt;

	public MessagingConfig() {
		super();
		this.properties = new HashMap<>();
	}

	public MessagingConfig(Properties properties) {
		super();
		properties.putAll(properties);
	}

	@Override
	public String getSetting(String name) {
		String setting = properties.get(name);
		if (setting != null && setting.startsWith("Crypt ")) {
			setting = crypt.decrypt(setting.substring(6));
		}
		return setting;
	}
	
	@Override
	public Properties getSettings(String prefix) {
		Properties prefixedProperties = new Properties();
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			String key = entry.getKey();
			if( key.startsWith(prefix)) { 
				prefixedProperties.put(key.substring(prefix.length()+1, key.length()), entry.getValue()); 
			}
		}
		return prefixedProperties;
	}

	@Override
	public ICrypt getCrypt() {
		return crypt;
	}

	@Override
	public void setCrypt(ICrypt crypt) {
		this.crypt = crypt;
	}

}
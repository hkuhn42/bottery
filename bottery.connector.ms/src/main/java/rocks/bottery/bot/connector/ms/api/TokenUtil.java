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
package rocks.bottery.bot.connector.ms.api;

import java.sql.Timestamp;

import rocks.bottery.bot.IBotConfig;

public class TokenUtil {

	private static final String	MICROSOFT_APP_PASSWORD = "MICROSOFT_APP_PASSWORD";

	private static final String	MICROSOFT_APP_ID	   = "MICROSOFT_APP_ID";

	private static TokenUtil	instance;

	private String				token				   = null;

	private Timestamp			tokenBestBefore		   = null;

	private static final String	SCOPE				   = "https://api.botframework.com/.default";
	private static final String	GRANT				   = "client_credentials";

	private IBotConfig			config;
	private String				name;

	private TokenUtil(String name, IBotConfig config) {
		this.config = config;
		this.name = name;
	}

	protected String ensureToken() {
		if (token != null && tokenBestBefore != null && tokenBestBefore.before(now())) {
			return token;
		}

		AuthenticationAPI client = BotClient.initRestProxy();
		String clientId = config.getSetting(name + "." + MICROSOFT_APP_ID);
		String clientSecret = config.getSetting(name + "." + MICROSOFT_APP_PASSWORD);
		AuthResponse response = client.requestToken(GRANT, clientId, clientSecret, SCOPE);
		if (response.getAccessToken() != null) {
			tokenBestBefore = new Timestamp(System.currentTimeMillis() + (response.getExpiresIn() * 1000));
			return response.getAccessToken();
		}
		return null;
	}

	public static TokenUtil getInstance(String name, IBotConfig config) {
		if (instance == null) {
			instance = new TokenUtil(name, config);
		}
		return instance;
	}

	public static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}
}

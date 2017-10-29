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
package rocks.bottery.bot.connector.ms;

import javax.ws.rs.core.Response;

import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IConnector;
import rocks.bottery.bot.connector.ms.api.Api2BotTokenVerifier;
import rocks.bottery.bot.connector.ms.api.MessageAPI;
import rocks.bottery.bot.connector.ms.model.Activity;

/**
 * Implementation of MessageAPI
 * 
 * @author Harald Kuhn
 */
public class MessageAPIImpl implements MessageAPI {

	private IBot				 bot;
	private IConnector			 connector;
	private Api2BotTokenVerifier verifier;

	public MessageAPIImpl(IBot bot, IConnector connector, String appId) {
		this.bot = bot;
		this.connector = connector;
		this.verifier = new Api2BotTokenVerifier(appId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.MessageAPI#receive(org.sylvani.bot.connector.ms.model. Activity)
	 */
	@Override
	public Response receive(String bearer, Activity message) {
		// only in dev mode
		if (bearer != null && bearer.length() > 0)
			verifier.verifyToken(bearer.replace("Bearer ", ""));
		bot.receive(new MSActivity(message), connector);
		return Response.ok().build();
	}

}

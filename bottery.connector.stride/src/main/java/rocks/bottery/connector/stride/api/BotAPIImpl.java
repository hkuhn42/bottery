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
package rocks.bottery.connector.stride.api;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;

import org.apache.cxf.helpers.IOUtils;
import org.apache.log4j.Logger;

/**
 * Basic implementation of the botApi
 * 
 * @author Harald Kuhn
 */
public class BotAPIImpl implements IBotAPI {

	private String baseUrl = null;

	public BotAPIImpl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String getDescriptor() {
		Logger.getLogger(BotAPIImpl.class).info("descriptor accessed");
		try {
			InputStream resourceAsStream = BotAPIImpl.class.getClassLoader().getResourceAsStream("rocks/bottery/connector/stride/descriptor.json");
			String descriptor = IOUtils.toString(resourceAsStream, "UTF-8");
			descriptor = descriptor.replace("${baseUrl}", baseUrl);
			descriptor = descriptor.replace("${connectorName}", "bottery");
			return descriptor;
		}
		catch (IOException e) {
			Logger.getLogger(BotAPIImpl.class).error("could not deliver descriptor", e);
			throw new WebApplicationException("descriptor not available", e);
		}
	}

	@Override
	public void installed(String authorization, String data) {
		Logger.getLogger(BotAPIImpl.class).info("bottery installed" + data);
	}

	@Override
	public void uninstalled(String authorization, String data) {
		Logger.getLogger(BotAPIImpl.class).info("bottery uninstalled" + data);

	}

	@Override
	public void mention(String authorization, Activity data) {
		Message message = data.getMessage();
		Logger.getLogger(BotAPIImpl.class).info("mention " + data.getSender().getId() + " " + message.getText() + " " + message.getBody().getType());
	}

	@Override
	public void directMessage(String authorization, Activity data) {
		Message message = data.getMessage();
		Logger.getLogger(BotAPIImpl.class).info("directMessage " + data.getSender().getId() + " " + message.getText() + " " + message.getBody().getType());
	}
}

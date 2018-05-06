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
package rocks.bottery.connector.stride;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.connector.stride.api.Activity;
import rocks.bottery.connector.stride.api.BotAPIImpl;
import rocks.bottery.connector.stride.api.Content;
import rocks.bottery.connector.stride.api.IAuthAPI;
import rocks.bottery.connector.stride.api.IStrideAPI;
import rocks.bottery.connector.stride.api.Message;
import rocks.bottery.connector.stride.api.Token;
import rocks.bottery.connector.stride.api.TokenRequest;
import rocks.bottery.messaging.IMessagingConfig;
import rocks.bottery.messaging.IReceiver;

/**
 * Web service based connector for atlassian stride
 * 
 * @author Harald Kuhn
 */
public class StrideConnector extends ConnectorBase {

	private static final String	API_ATLASSIAN_COM = "https://api.atlassian.com";

	public static final String	CHANNEL			  = "stride";

	private String				connectorName	  = CHANNEL;

	private String				address;

	public static String		LOCAL_ADDRESS	  = "http://localhost";

	public static String		LOCAL_PORT		  = "3974";

	private String				clientId		  = null;
	private String				clientSecret	  = null;
	private String				cloudId			  = null;
	private String				publicUrl		  = null;

	private Token				token;

	private Server				server;

	private IStrideAPI			client;
	private IAuthAPI			authClient;

	private JWTUtils			jwtUtils;

	@Override
	public void init(IMessagingConfig config) {
		super.init(config);
		clientId = config.getSetting(connectorName + ".clientId");
		clientSecret = config.getSetting(connectorName + ".clientSecret");
		cloudId = config.getSetting(connectorName + ".cloudId");
		publicUrl = config.getSetting(connectorName + ".publicUrl");
		jwtUtils = new JWTUtils(clientSecret, cloudId);
	}

	@Override
	public void send(IActivity response) {

		Token token = ensureToken();

		if (client == null) {
			client = initStrideRestProxy(IStrideAPI.class, API_ATLASSIAN_COM);
			configureLogging(client);
		}
		Message message = new Message();
		message.getBody().setVersion(1);
		message.getBody().setType("doc");
		Content paragraph = new Content();
		paragraph.setType("paragraph");
		Content mainContent = new Content();

		// add choices
		StringBuilder builder = new StringBuilder();
		builder.append(response.getText());
		if (response.getChoices() != null && response.getChoices().size() > 0) {
			builder.append("\n");
			for (int i = 0; i < response.getChoices().size(); i++) {
				builder.append((i + 1) + ": ");
				builder.append(response.getChoices().get(i).getLabelString());
				builder.append("\n");
			}
		}

		mainContent.setText(builder.toString());

		mainContent.setType("text");
		paragraph.getContent().add(mainContent);
		message.getBody().getContent().add(paragraph);
		client.sendMessageToRoom("Bearer " + token.getAccessToken(), cloudId, response.getConversation().getId(), message);
	}

	private Token ensureToken() {
		if (token != null && (token.getIssued() + token.getExpiresIn() * 1000) < System.currentTimeMillis()) {
			return token;
		}

		TokenRequest request = new TokenRequest("client_credentials", clientId, clientSecret);
		if (authClient == null) {
			authClient = initAuthRestProxy(IAuthAPI.class, API_ATLASSIAN_COM);
			configureLogging(authClient);
		}

		token = authClient.getToken(request);
		token.setIssued(System.currentTimeMillis() - 2000);
		return token;
	}

	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant();
	}

	@Override
	public String getChannel() {
		return CHANNEL;
	}

	@Override
	public void register(IReceiver receiver) {
		publish(BotAPIImpl.class, new BotAPIImpl(publicUrl) {
			@Override
			public void directMessage(String authorization, Activity activity) {
				Logger.getLogger(StrideConnector.class).debug("directMessage " + activity);
				if (isValid(authorization)) {
					receiver.receive(new StrideActivity(activity), StrideConnector.this);
				}
				else {
					Logger.getLogger(StrideConnector.class).warn("rejected message because the auth header was invalid");
				}
			}

			@Override
			public void mention(String authorization, Activity activity) {
				Logger.getLogger(StrideConnector.class).debug("mention received " + activity);
				if (isValid(authorization)) {
					receiver.receive(new StrideActivity(activity), StrideConnector.this);
				}
				else {
					Logger.getLogger(StrideConnector.class).warn("rejected message because the auth header was invalid");
				}
			}
		});

	}

	protected boolean isValid(String authorization) {
		return jwtUtils.validate(authorization);
	}

	// FIXME: move to utility
	protected void publish(Class<?> api, Object impl) {

		if (address == null) {
			address = LOCAL_ADDRESS + ":" + LOCAL_PORT;
		}

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(api);

		sf.setResourceProvider(api, new SingletonResourceProvider(impl));
		List<Object> providers = new ArrayList<>();
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		providers.add(provider);
		sf.setProviders(providers);
		sf.setAddress(address);
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		sf.getBus().getInInterceptors().add(new LoggingInInterceptor());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		server = sf.create();
	}

	protected static IAuthAPI initAuthRestProxy(Class<IAuthAPI> clazz, String url) {
		List<Object> providers = new ArrayList<>();
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		// provider.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		providers.add(provider);
		IAuthAPI inbox = JAXRSClientFactory.create(url, clazz, providers);
		return inbox;
	}

	protected static IStrideAPI initStrideRestProxy(Class<IStrideAPI> clazz, String url) {
		List<Object> providers = new ArrayList<>();
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		// provider.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		providers.add(provider);
		IStrideAPI inbox = JAXRSClientFactory.create(url, clazz, providers);
		return inbox;
	}

	protected static void configureLogging(Object proxy) {
		Bus bus = WebClient.getConfig(proxy).getBus();
		bus.getInInterceptors().add(new LoggingInInterceptor());
		bus.getOutInterceptors().add(new LoggingOutInterceptor());
	}

	@Override
	public void shutdown() {
		server.stop();
		super.shutdown();
	}
}

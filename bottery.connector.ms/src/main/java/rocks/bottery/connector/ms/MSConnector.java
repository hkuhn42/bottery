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
package rocks.bottery.connector.ms;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.SerializationConfig;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.ms.model.Activity;
import rocks.bottery.bot.connector.ms.model.ChannelAccount;
import rocks.bottery.bot.connector.ms.model.ConversationAccount;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.connector.ms.api.BotClient;
import rocks.bottery.connector.ms.api.MessageAPI;
import rocks.bottery.messaging.IReceiver;

/**
 * Connector implementation for the ms bot apis
 * 
 * @author Harald Kuhn
 */
public class MSConnector extends ConnectorBase {

	private static final String	MICROSOFT_APP_ID = "MICROSOFT_APP_ID";

	private BotClient			client;

	private String				address;

	public static String		LOCAL_ADDRESS	 = "http://localhost";

	public static String		LOCAL_PORT		 = "3978";

	private Server				server;

	private String				name;

	public MSConnector() {
		this("msBotFramework");
	}

	public MSConnector(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConnector#listen(org.sylvani.bot.IBot)
	 */
	@Override
	public void register(IReceiver receiver) {

		this.client = new BotClient(name, config);
		MessageAPIImpl messageApi = new MessageAPIImpl(receiver, this, config.getSetting(name + "." + MICROSOFT_APP_ID));
		this.address = config.getSetting(name + ".serverAddress");
		publish(messageApi);
	}

	protected void publish(MessageAPIImpl messageApi) {

		if (address == null) {
			address = LOCAL_ADDRESS + ":" + LOCAL_PORT;
		}

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(MessageAPI.class);

		sf.setResourceProvider(MessageAPI.class, new SingletonResourceProvider(messageApi));
		List<Object> providers = new ArrayList<>();
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		provider.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		provider.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConnector#send(java.lang.Object)
	 */
	@Override
	public void send(IActivity activity) {
		Activity msActivity = null;
		if (activity instanceof MSActivity) {
			msActivity = ((MSActivity) activity).getActivity();
		}
		else {
			msActivity = new Activity();
			msActivity.setText(activity.getText());
			msActivity.setTopicName(activity.getTopic());
		}
		client.send(msActivity);
	}

	@Override
	public String getChannel() {
		return "ms";
	}

	@Override
	public IParticipant getConnectorAccount() {
		return null;
	}

	@Override
	public IActivity newMessageTo(IParticipant recipient) {
		Activity activity = new Activity();
		activity.setType("message");
		activity.setId("A" + new SecureRandom().nextLong());
		ChannelAccount account = toAccount(recipient);
		activity.setRecipient(account);

		MSActivity msActivity = new MSActivity(activity);
		return msActivity;
	}

	@Override
	public IActivity newReplyTo(IActivity toThisActivity) {
		MSActivity activity = (MSActivity) newMessageTo(toThisActivity.getFrom());
		activity.setFrom(toThisActivity.getRecipient());
		ConversationAccount conversation = new ConversationAccount();
		conversation.setId(toThisActivity.getConversation().getId());
		conversation.setName(toThisActivity.getConversation().getChannel());
		activity.getActivity().setConversation(conversation);
		activity.getActivity().setReplyToId(toThisActivity.getId());
		if (toThisActivity instanceof MSActivity) {
			activity.getActivity().setServiceUrl(((MSActivity) toThisActivity).getActivity().getServiceUrl());
		}

		return activity;
	}

	@Override
	public void shutdown() {
		server.stop();
		server.destroy();
	}

	private ChannelAccount toAccount(IParticipant recipient) {
		ChannelAccount account = new ChannelAccount();
		account.setId(recipient.getId());
		account.setName(recipient.getName());
		return account;
	}
}

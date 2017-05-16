/**
 *    Copyright (C) 2016-2017 Harald Kuhn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.bot.connector.ms;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.SerializationConfig;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IConnector;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.ms.api.BotClient;
import rocks.bottery.bot.connector.ms.api.MessageAPI;
import rocks.bottery.bot.connector.ms.model.Activity;

/**
 * Connector implementation for the ms bot apis
 * 
 * @author Harald Kuhn
 */
public class MSConnector implements IConnector {

	private BotClient	 client;

	private String		 address;

	public static String LOCAL_ADDRESS = "http://localhost";

	public static String LOCAL_PORT	   = "3978";

	public MSConnector() {
		this(LOCAL_ADDRESS + ":" + LOCAL_PORT);
	}

	public MSConnector(int port) {
		this(LOCAL_ADDRESS + ":" + port);
	}

	public MSConnector(String address) {
		this.client = new BotClient();
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConnector#listen(org.sylvani.bot.IBot)
	 */
	@Override
	public void listen(IBot handler) {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(MessageAPI.class);
		sf.setResourceProvider(MessageAPI.class, new SingletonResourceProvider(new MessageAPIImpl(handler)));
		List<Object> providers = new ArrayList<>();
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		provider.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		providers.add(provider);
		sf.setProviders(providers);
		sf.setAddress(address);
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		sf.create();
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
		}
		client.send(msActivity);
	}

	@Override
	public IParticipant getConnectorAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IActivity newMessageTo(IParticipant recipientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IActivity newReplyTo(IActivity toThisActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void shutdown() {
		
	}
}

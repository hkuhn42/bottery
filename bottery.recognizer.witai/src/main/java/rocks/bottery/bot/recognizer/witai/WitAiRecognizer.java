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
package rocks.bottery.bot.recognizer.witai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.log4j.BasicConfigurator;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import rocks.bottery.bot.BotConfig;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.bot.recognizers.RecognizerBase;
import rocks.bottery.connector.GenericActivity;

/**
 * A recognizer using wit.ai service to identify the useres intent
 * 
 * @author Harald Kuhn
 */
public class WitAiRecognizer extends RecognizerBase {

	private static final String	HTTPS_API_WIT_AI = "https://api.wit.ai";

	private static final String	API_VERSION		 = "20180430";

	private WitAiApi			api;
	private String				token;

	@Override
	public void init(IBotConfig config) throws IOException {
		super.init(config);
		this.token = config.getSetting("witAi.token");
		api = initConversationRestProxy(HTTPS_API_WIT_AI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IRecognizer#recognize(rocks.bottery.bot.ISession, rocks.bottery.bot.IActivity)
	 */
	@Override
	public IIntent recognize(ISession session, IActivity activity) {

		Response intent = api.message("Bearer " + token, API_VERSION, activity.getText());
		if (intent.getOutcomes().size() > 1) {
			intent.getOutcomes().sort(new Comparator<Outcome>() {
				@Override
				public int compare(Outcome outcome1, Outcome outcome2) {
					return new Double(outcome1.getConfidence()).compareTo(outcome2.getConfidence());
				}
			});
		}

		System.out.println("intent " + intent.getText());

		for (String key : intent.getAdditionalProperties().keySet()) {
			System.out.println(key + " " + intent.getAdditionalProperties().get(key));
		}

		for (Outcome outcome : intent.getOutcomes()) {

			System.out.println("outcome " + outcome.getIntent() + " " + outcome.getText() + " " + outcome.getConfidence());

			for (Datetime entity : outcome.getEntities().getDatetime()) {
				System.out.println(entity.getType() + " " + entity.getValue());
				for (String key : entity.getAdditionalProperties().keySet()) {
					System.out.println(key + " " + entity.getAdditionalProperties().get(key));
				}
			}

			for (Location entity : outcome.getEntities().getLocation()) {
				System.out.println(entity.getType() + " " + entity.getValue());
				for (String key : entity.getAdditionalProperties().keySet()) {
					System.out.println(key + " " + entity.getAdditionalProperties().get(key));
				}
			}

			for (Entity entity : outcome.getEntities().getEntities()) {
				System.out.println(entity.getEntityType() + " " + entity.getType() + " - " + entity.getValue());
				for (String key : entity.getAdditionalProperties().keySet()) {
					System.out.println(key + " " + entity.getAdditionalProperties().get(key));
				}
			}

		}

		return null;
	}

	protected static WitAiApi initConversationRestProxy(String url) {
		List<Object> providers = new ArrayList<>();
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		// provider.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		providers.add(provider);
		WitAiApi proxy = JAXRSClientFactory.create(url, WitAiApi.class, providers);
		configureLogging(proxy);
		return proxy;
	}

	protected static void configureLogging(Object proxy) {
		Bus bus = WebClient.getConfig(proxy).getBus();
		bus.getInInterceptors().add(new LoggingInInterceptor());
		bus.getOutInterceptors().add(new LoggingOutInterceptor());
	}

	protected static void configureConduit(Object proxy) {
		HTTPConduit conduit = (HTTPConduit) WebClient.getConfig(proxy).getConduit();
		TLSClientParameters params = new TLSClientParameters();
		conduit.setTlsClientParameters(params);
	}

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();

		WitAiRecognizer r = new WitAiRecognizer();
		BotConfig config = new BotConfig();
		config.setSetting("witAi.token", args[0]);
		r.init(config);
		GenericActivity activity = new GenericActivity();
		activity.setText("i would like to order a Salami Pizza");
		r.recognize(null, activity);
	}
}

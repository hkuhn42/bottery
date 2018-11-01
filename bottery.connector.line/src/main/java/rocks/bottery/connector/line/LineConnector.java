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
package rocks.bottery.connector.line;

import java.util.concurrent.ExecutionException;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.log4j.Logger;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericActivity;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.messaging.IMessagingConfig;
import rocks.bottery.messaging.IReceiver;
import rocks.bottery.util.StandalonePublisher;

/**
 * Connector for line
 * 
 * Currently only supports replying with replyToken. Using {@link #newReplyTo(IActivity)} with an activiy received by
 * line takes care of this automatically
 * 
 * @author Harald Kuhn
 */
public class LineConnector extends ConnectorBase {

	public static final String REPLY_TOKEN = "replyToken";

	@Override
	public void init(IMessagingConfig config) {
		super.init(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.connector.IConnector#register(rocks.bottery.messaging.IReceiver)
	 */
	@Override
	public void register(final IReceiver receiver) {
		JAXRSServerFactoryBean factory = StandalonePublisher.prepare(new Handler(receiver), "http://localhost:10020");
		factory.getBus().getInInterceptors().add(new MessageDigestInterceptor(config.getSetting("line.channelSecret")));
		factory.create();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.connector.IConnector#send(rocks.bottery.bot.IActivity)
	 */
	@Override
	public void send(IActivity activity) {
		String channelToken = config.getSetting("line.channelToken");
		LineMessagingClient client = LineMessagingClient.builder(channelToken).build();

		TextMessage textMessage = new TextMessage(activity.getText());
		ReplyMessage replyMessage = new ReplyMessage(String.valueOf(activity.getAttribute(REPLY_TOKEN)), textMessage);

		final BotApiResponse botApiResponse;
		try {
			botApiResponse = client.replyMessage(replyMessage).get();
		}
		catch (InterruptedException | ExecutionException e) {
			Logger.getLogger(LineConnector.class).error("send failed", e);
			return;
		}
		Logger.getLogger(LineConnector.class).debug(botApiResponse);
	}

	@Override
	public GenericActivity newReplyTo(IActivity toThisActivity) {
		GenericActivity activity = super.newReplyTo(toThisActivity);
		activity.setFrom(toThisActivity.getRecipient());
		activity.setAttribute(REPLY_TOKEN, toThisActivity.getAttribute(REPLY_TOKEN));
		return activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.connector.IConnector#getConnectorAccount()
	 */
	@Override
	public IParticipant getConnectorAccount() {
		return PARTICIPANT;
	}

	public static GenericParticipant PARTICIPANT = new GenericParticipant("bottery", "bottery", "line", "bot@bottery");

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.connector.IConnector#getChannel()
	 */
	@Override
	public String getChannel() {
		return "line";
	}

	public final class Handler implements ILineWebhookAPI {

		private IReceiver receiver;

		public Handler(IReceiver receiver) {
			this.receiver = receiver;
		}

		@Override
		public String receive(String signature, Events events) {
			// check wether to to async processing
			for (Event event : events.getEvents()) {
				if (receiver != null)
					receiver.receive(new LineActivity(event), LineConnector.this);
			}
			return "ok";
		}
	}
}
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
package rocks.bottery.connector.generic;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericActivity;
import rocks.bottery.connector.GenericConversation;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.connector.handoff.IHandoffConnector;
import rocks.bottery.messaging.IReceiver;
import rocks.bottery.util.ClientFactory;
import rocks.bottery.util.StandalonePublisher;

/**
 * A generic connector for integrating messaging services
 * 
 * @author Harald Kuhn
 */
public class GenericConnector extends ConnectorBase implements IHandoffConnector {

	public final class Handler implements IMessagingAPI {

		private IReceiver receiver;

		public Handler(IReceiver receiver) {
			this.receiver = receiver;
		}

		@Override
		public String deliver(Activity[] tfActivities) {
			for (Activity tfActivity : tfActivities) {
				GenericActivity activity = convert(tfActivity);
				receiver.receive(activity, GenericConnector.this);
			}
			// prevent a problem with many proxies on post with empty response body
			return "ok";
		}
	}

	public static final String CHANNEL = "turnfriendly";

	private IMessagingAPI	   proxy;

	@Override
	public void register(IReceiver receiver) {
		proxy = new ClientFactory<IMessagingAPI>().prepareClientProxy("http://harald5520.fritz.box:8048/turnfriendly/ws/rest", IMessagingAPI.class);
		StandalonePublisher.publish(new Handler(receiver), "http://localhost:9090");

	}

	@Override
	public void send(IActivity activity) {
		Logger.getLogger(GenericConnector.class).debug("send activity from " + activity.getFrom().getAddress() + " to " + activity.getRecipient().getAddress());
		Activity tfActivity = convert(activity);
		proxy.deliver(new Activity[] { tfActivity });
	}

	protected static Activity convert(IActivity activity) {
		Activity tfActivity = new Activity();
		tfActivity.setId(activity.getId());
		tfActivity.setText(activity.getText());
		tfActivity.setDate(new Date());
		tfActivity.setType(ActivityType.MESSAGE);
		tfActivity.setTo(convert(activity.getRecipient()));
		tfActivity.setFrom(convert(activity.getFrom()));
		tfActivity.setConversation(new Conversation(activity.getConversation().getId(), activity.getConversation().getChannel()));
		return tfActivity;
	}

	protected static Participant convert(IParticipant from) {
		return new Participant(from.getId(), from.getAddress(), from.getName());
	}

	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("dummy", "dummy", getChannel(), "dummy#tf.bottery");
	}

	@Override
	public String getChannel() {
		return CHANNEL;
	}

	protected static GenericActivity convert(Activity tfActivity) {
		GenericActivity activity = new GenericActivity();
		activity.setId(tfActivity.getId());
		activity.setType(rocks.bottery.bot.ActivityType.MESSAGE);
		activity.setText(tfActivity.getText());
		activity.setTopic(tfActivity.getTopic());
		activity.setFrom(convert(tfActivity.getFrom()));
		activity.setRecipient(convert(tfActivity.getTo()));
		activity.setConversation(new GenericConversation(tfActivity.getConversation().getId(), CHANNEL, GenericConnector.class.getName()));
		return activity;
	}

	protected static GenericParticipant convert(Participant convert) {
		return new GenericParticipant(convert.getId(), convert.getName(), CHANNEL, convert.getAddress());
	}

	@Override
	public void handoff(IConversation conversation, List<IActivity> history) {
		Activity[] activities = new Activity[history.size() + 1];
		for (int i = 0; i < history.size(); i++) {
			activities[i] = convert(history.get(i));
		}
		Activity handoff = new Activity();
		handoff.setConversation(activities[0].getConversation());
		handoff.setType(ActivityType.HANDOFF);
		handoff.setFrom(convert(history.get(history.size()).getFrom()));
		handoff.setTo(convert(getConnectorAccount()));
		activities[history.size() + 1] = handoff;

		proxy.deliver(activities);
	}
}

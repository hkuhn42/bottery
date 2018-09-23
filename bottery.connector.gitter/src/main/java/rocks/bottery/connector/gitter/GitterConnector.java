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
package rocks.bottery.connector.gitter;

import java.util.List;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.console.ExecutorConnectorBase;
import rocks.bottery.messaging.IReceiver;
import rocks.bottery.util.ClientFactory;

/**
 * Connector for gitter using the gitter rest api. This connector monitors one room.
 * 
 * @see <a href="https://developer.gitter.im/docs/rest-api">developer.gitter.im</a>
 *
 * @author Harald Kuhn
 *
 */
public class GitterConnector extends ExecutorConnectorBase {

	protected static final String HTTPS_API_GITTER_IM_V1 = "https://api.gitter.im/v1";

	public static final String	  CHANNEL				 = "gitter";

	private GitterAPI			  proxy;

	private GitterParticipant	  botUser;
	private GitterConversation	  gitterRoom;

	@Override
	public void register(IReceiver receiver) {
		proxy = new ClientFactory<GitterAPI>().prepareClientProxy(HTTPS_API_GITTER_IM_V1, GitterAPI.class);

		String token = config.getSetting("gitter.token");
		User user = proxy.getUser("Bearer " + token, "me");

		botUser = new GitterParticipant(user);

		List<Room> response = proxy.listRooms("Bearer " + token, null);
		for (Room room : response) {
			if (room.getId().equals(config.getSetting("gitter.roomId"))) {
				gitterRoom = new GitterConversation(room);
				break;
			}
		}

		executor.submit(new Runnable() {

			@Override
			public void run() {
				listen(receiver);
			}

			public void listen(IReceiver handler) {
				List<Message> messages = proxy.fetchMessages("Bearer " + token, config.getSetting("gitter.roomId"), 20, null, null, null, null);
				for (Message message : messages) {
					if (message.isUnread()) {

						proxy.markAsRead("Bearer " + token, botUser.getId(), config.getSetting("gitter.roomId"), new MessageList(message.getId()));

						IActivity activity = new GitterActivity(message, botUser, gitterRoom);
						handler.receive(activity, GitterConnector.this);

					}
				}
				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				listen(handler);
			}
		});
	}

	@Override
	public void send(IActivity activity) {

		StringBuilder builder = new StringBuilder();
		builder.append(activity.getText());
		if (activity.getChoices() != null && activity.getChoices().size() > 0) {
			builder.append("\n");
			for (int i = 0; i < activity.getChoices().size(); i++) {
				builder.append((i + 1) + ": ");
				builder.append(activity.getChoices().get(i).getLabelString());
				builder.append("\n");
			}
		}

		proxy.sendMessages("Bearer " + config.getSetting("gitter.token"), config.getSetting("gitter.roomId"), builder.toString());
	}

	@Override
	public IParticipant getConnectorAccount() {
		return botUser;
	}

	@Override
	public String getChannel() {
		return CHANNEL;
	}
}

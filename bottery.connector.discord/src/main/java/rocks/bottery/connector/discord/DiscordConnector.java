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
package rocks.bottery.connector.discord;

import org.apache.log4j.Logger;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.Channel;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.messaging.IReceiver;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Connector for discord
 *
 * The connector needs an access token. This can be generated with a call of the following url after replacing <appid>
 * with your appid
 *
 * https://discordapp.com/api/oauth2/authorize?client_id=<appid>&scope=bot&permissions=1
 * 
 * @author Harald Kuhn
 *
 */
public class DiscordConnector extends ConnectorBase {

	private IDiscordClient client;
	private IReceiver	   receiver;

	@Override
	public void register(IReceiver receiver) {
		this.receiver = receiver;
		try {
			client = createClient(config.getSetting("discord.token"));
			EventDispatcher dispatcher = client.getDispatcher();
			dispatcher.registerListener(this);
		}
		catch (DiscordException e) {
			Logger.getLogger(DiscordConnector.class).error("could not create discord client", e);
		}
	}

	@Override
	public void send(IActivity activity) {
		IChannel channel = (IChannel) activity.getConversation().getConnectorConversation();
		// send via request buffer to prevent api "overload"
		RequestBuffer.request(() -> {
			try {
				channel.sendMessage(activity.getText());
			}
			catch (DiscordException e) {
				Logger.getLogger(DiscordConnector.class).error("Message could not be sent with error: ", e);
			}
		});
	}

	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("discord", "discord", "discord");
	}

	@Override
	public String getChannel() {
		return Channel.DISCORD.name();
	}

	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event) {
		receiver.receive(new DiscordActivity(event.getMessage(), getConnectorAccount()), this);
	}

	@Override
	public void shutdown() {
		client.logout();
	}

	/**
	 * Instatiates the IDiscordClient with the given token
	 * 
	 * @param token
	 * @param login
	 * @return
	 */
	protected static IDiscordClient createClient(String token) throws DiscordException {
		ClientBuilder clientBuilder = new ClientBuilder();
		clientBuilder.withToken(token);
		return clientBuilder.login();
	}
}

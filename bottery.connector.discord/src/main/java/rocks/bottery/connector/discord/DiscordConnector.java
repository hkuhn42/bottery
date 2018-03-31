package rocks.bottery.connector.discord;

import org.apache.log4j.Logger;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
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
 * Call after replacing <appid> with your appid
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
		client = createClient(config.getSetting("discord.token"), true);
		EventDispatcher dispatcher = client.getDispatcher();
		dispatcher.registerListener(this);
	}

	@Override
	public void send(IActivity activity) {
		IChannel channel = (IChannel) activity.getConversation().getConnectorConversation();
		// This might look weird but it'll be explained in another page.
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
		return null;
	}

	@Override
	public String getChannel() {
		return "discord";
	}

	public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord
	                                                                         // client
		ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
		clientBuilder.withToken(token); // Adds the login info to the builder
		try {
			if (login) {
				return clientBuilder.login(); // Creates the client instance and logs the client in
			}
			else {
				return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you
				                              // would have to call client.login() yourself
			}
		}
		catch (DiscordException e) { // This is thrown if there was a problem building the client
			Logger.getLogger(DiscordConnector.class).error("could not create discord client", e);
			return null;
		}
	}

	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event) {
		receiver.receive(new DiscordActivity(event.getMessage()), this);
	}

	@Override
	public void shutdown() {
		client.logout();
	}
}

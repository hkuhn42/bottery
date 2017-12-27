package rocks.bottery.connector.discord;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * synchrounous connecctor to amazon alexa
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
	private IBot		   bot;

	@Override
	public void register(IBot bot) {
		this.bot = bot;
		client = createClient(bot.getBotConfig().getSetting("discord.token"), true);
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
				System.err.println("Message could not be sent with error: ");
				e.printStackTrace();
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
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		DiscordConnector connector = new DiscordConnector();
		connector.register(null);
	}

	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event) {
		bot.receive(new DiscordActivity(event.getMessage()), this);
	}

	@Override
	public void shutdown() {
		client.logout();
	}
}

/**
 * 
 */
package rocks.bottery.connector.discord;

import rocks.bottery.bot.IConversation;
import sx.blah.discord.handle.obj.IChannel;

/**
 * @author Harald Kuhn
 *
 */
public class DiscordConversation implements IConversation {
	private IChannel channel;
	private String	 connectorId;

	public DiscordConversation(IChannel channel, String connectorId) {
		this.channel = channel;
		this.connectorId = connectorId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getId()
	 */
	@Override
	public String getId() {
		return channel.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getChannel()
	 */
	@Override
	public String getChannel() {
		return channel.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConversation#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		return connectorId;
	}

	@Override
	public Object getConnectorConversation() {
		return channel;
	}
}

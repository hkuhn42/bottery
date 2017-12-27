/**
 * 
 */
package rocks.bottery.connector.discord;

import rocks.bottery.bot.IParticipant;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Harald Kuhn
 *
 */
public class DiscordIParticipant implements IParticipant {

	private IUser user;

	public DiscordIParticipant(IUser user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getId()
	 */
	@Override
	public String getId() {
		return user.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#getName()
	 */
	@Override
	public String getName() {
		return user.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IParticipant#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

}

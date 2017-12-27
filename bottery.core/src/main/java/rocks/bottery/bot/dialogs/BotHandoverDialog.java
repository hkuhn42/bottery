/**
 * 
 */
package rocks.bottery.bot.dialogs;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.ISession;
import rocks.bottery.connector.IConnector;

/**
 * @author Harald Kuhn
 *
 */
public class BotHandoverDialog implements IDialog {

	private IConnector handoverConnector;
	private IBot	   handoverBot;

	public BotHandoverDialog(IBot handoverBot) {
		this.handoverBot = handoverBot;
		handoverConnector = null; // new loopack connector
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IHandler#handle(rocks.bottery.bot.ISession, rocks.bottery.bot.IActivity)
	 */
	@Override
	public void handle(ISession session, IActivity activity) {
		handoverBot.handle(session, activity);
	}
}
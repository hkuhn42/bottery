/**
 * 
 */
package bottery.core;

import java.util.Stack;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;

/**
 * @author Harald Kuhn
 *
 */
public class TestConnector extends ConnectorBase {

	private Stack<IActivity> testActivities;
	private IBot			 bot;

	public TestConnector(Stack<IActivity> testActivities) {
		this.testActivities = testActivities;
		bot.receive(testActivities.pop(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#listen(rocks.bottery.bot.IBot)
	 */
	@Override
	public void register(IBot bot) {
		this.bot = bot;
		bot.receive(testActivities.pop(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#shutdown()
	 */
	@Override
	public void shutdown() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#send(rocks.bottery.bot.IActivity)
	 */
	@Override
	public void send(IActivity data) {
		System.out.println("got: " + data.getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#getConnectorAccount()
	 */
	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("1", "Chuck");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.connector.console.ConnectorBase#getChannel()
	 */
	@Override
	public String getChannel() {
		return "test";
	}

}

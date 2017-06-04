/**
 * 
 */
package rocks.bottery.examples;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IConnector;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;;

/**
 * A basic hello world example bot
 * 
 * @author Harald Kuhn
 *
 */
public class HelloBot extends UniversalBot {

	public HelloBot(IConnector connector) {
		super(connector);

		setWelcomeDialog(new Utterance() {
			@Override
			public IModel<String> getText(IActivity request, ISession session) {
				return new Model<String>("Hello World");
			}
		});
	}
}

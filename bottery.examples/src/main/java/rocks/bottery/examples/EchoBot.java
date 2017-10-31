/**
 * 
 */
package rocks.bottery.examples;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;;

/**
 * A basic echo example bot
 * 
 * @author Harald Kuhn
 */
public class EchoBot extends UniversalBot {

	public EchoBot() {
		setWelcomeDialog(new Utterance() {
			@Override
			public IModel<String> getText(IActivity request, ISession session) {
				return new Model<String>(request.getText());
			}
		});
	}
}

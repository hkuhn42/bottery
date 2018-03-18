/**
 * 
 */
package rocks.bottery.examples.hello.luis;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.recognizer.luis.LuisRecognizer;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;;

/**
 * A basic bot showing the luis recognizer
 * 
 * @author Harald Kuhn
 */
public class AlarmBot extends UniversalBot {

	public AlarmBot() {
		// set a welcome dialog - this ís not necessary if the recognizer is set to greedy as it always jumps in but its
		// good custom
		setWelcomeDialog(new Utterance() {
			@Override
			public IModel<String> getText(IActivity request, ISession session) {
				return new Model<String>("hello");
			}
		});
		// lets use luis api as recognizer
		LuisRecognizer recognizer = new LuisRecognizer();
		// map the luis intents to intents used in the dialogs (keeps them portable)
		recognizer.getIntentMapping().put("builtin.intent.alarm.delete_alarm", "deleteAlarm");
		// add a dialog which only responds with ok if it knows the intent or a short help text
		getBotConfig().getRecognizers().add(recognizer);

		addDialog("deleteAlarm", new Utterance() {
			@Override
			public IModel<String> getText(IActivity request, ISession session) {
				if (request.getIntent() != null && "deleteAlarm".equalsIgnoreCase(request.getIntent().getIntentName())) {
					return new Model<String>("ok");
				}
				return new Model<String>("sorry i can only delete alarms");
			}
		});
	}
}

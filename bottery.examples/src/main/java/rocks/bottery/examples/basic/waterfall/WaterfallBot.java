/**
 * 
 */
package rocks.bottery.examples.basic.waterfall;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IConnector;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.connector.console.ConsoleConnector;
import rocks.bottery.bot.dialogs.Decision;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.bot.dialogs.Interview;
import rocks.bottery.bot.dialogs.Question;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;
import rocks.bottery.bot.util.SessionModel;;

/**
 * @author Harald Kuhn
 *
 */
public class WaterfallBot extends UniversalBot {

	public WaterfallBot(IConnector connector) {
		super(connector);
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		WaterfallBot bot = new WaterfallBot(new ConsoleConnector());

		List<Choice<String>> choices = new ArrayList<>();
		choices.add(new Choice<String>("Java", "Java"));
		choices.add(new Choice<String>("Groovy", "Groovy"));
		choices.add(new Choice<String>("Scala", "Scala"));

		// String[] choices = new String[] { "Java", "Groovy", "Scala" };

		bot.setWelcomeDialog(new Interview(new IDialog[] {

		        new Question<String>(new SessionModel<>("name")) {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("hello"));
			        };
		        },

		        new Question<Integer>(new SessionModel<>("experience")) {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("yearsCoding"));
			        };
		        },

		        new Decision<String>(new SessionModel<>("language"), choices) {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("whatLanguage"));
			        };
		        },
		        //
		        // new IDialog() {
		        //
		        // @Override
		        // public void handle(ISession session, IActivity activity) {
		        // IActivity reply = session.getConnector().newReplyTo(activity);
		        // String experience = activity.getText();
		        // session.setAttribute("experience", experience);
		        // StringBuilder text = new StringBuilder("What language do you code Java in?");
		        // String[] choices = new String[] { "Java", "Groovy", "Scala" };
		        // for (int i = 0; i < choices.length; i++) {
		        // text.append("\n" + (i + 1) + "." + choices[i]);
		        // }
		        // reply.setText(text.toString());
		        // session.send(reply);
		        // }
		        //
		        // },

		        new Utterance() {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("gotIt"));
			        };
		        }

		}));

	}

}

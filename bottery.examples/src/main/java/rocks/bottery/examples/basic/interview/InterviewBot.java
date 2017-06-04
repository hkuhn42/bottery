/**
 * 
 */
package rocks.bottery.examples.basic.interview;

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
import rocks.bottery.bot.dialogs.IValidator;
import rocks.bottery.bot.dialogs.Interview;
import rocks.bottery.bot.dialogs.Question;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;
import rocks.bottery.bot.util.SessionPropertyModel;;

/**
 * @author Harald Kuhn
 *
 */
public class InterviewBot extends UniversalBot {

	public InterviewBot(IConnector connector) {
		super(connector);

		List<Choice<String>> choices = new ArrayList<>();
		choices.add(new Choice<String>("Java", "Java"));
		choices.add(new Choice<String>("Groovy", "Groovy"));
		choices.add(new Choice<String>("Scala", "Scala"));

		// String[] choices = new String[] { "Java", "Groovy", "Scala" };

		setWelcomeDialog(new Interview("statsBean", DeveloperStatsBean.class, new IDialog[] {

		        new Question<String>(new SessionPropertyModel<>("statsBean", "name")) {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("hello"));
			        };
		        },

		        new Question<Integer>(new SessionPropertyModel<>("statsBean", "experience")) {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("yearsCoding"));
			        }

			        @Override
			        public IValidator<Integer> getAnswerValidator() {
				        return super.getAnswerValidator();
			        }
		        },

		        new Decision<String>(new SessionPropertyModel<>("statsBean", "mainLanguage"), choices) {
			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("whatLanguage"));
			        };
		        },

		        new Utterance() {

			        @Override
			        public void handle(ISession session, IActivity activity) {
				        super.handle(session, activity);
				        System.out.println(session.getAttribute("statsBean"));
			        }

			        @Override
			        public IModel<String> getText(IActivity request, ISession session) {
				        return new Model<String>(session.getResolvedResource("gotIt"));
			        };
		        }

		}));

	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new InterviewBot(new ConsoleConnector());

	}

}

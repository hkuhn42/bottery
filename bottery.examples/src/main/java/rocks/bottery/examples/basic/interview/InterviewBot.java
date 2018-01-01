/**
 * 
 */
package rocks.bottery.examples.basic.interview;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Decision;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.bot.dialogs.IValidator;
import rocks.bottery.bot.dialogs.Interview;
import rocks.bottery.bot.dialogs.Question;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;
import rocks.bottery.bot.util.SessionPropertyModel;
import rocks.bottery.connector.console.ConsoleConnector;;

/**
 * @author Harald Kuhn
 *
 */
public class InterviewBot extends UniversalBot {

	public InterviewBot() {
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
				        List<IActivity> activities = session.getBot().getBotConfig().getArchive()
				                .getActivitiesByConversation(activity.getConversation().getId());
				        for (IActivity a : activities) {
					        System.out.println(a.getFrom() + " " + a.getText());
				        }
				        session.invalidate();
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
		ConsoleConnector consoleConnector = new ConsoleConnector();

		InterviewBot handler = new InterviewBot();
		consoleConnector.init(handler.getBotConfig());
		consoleConnector.register(handler);

	}

}

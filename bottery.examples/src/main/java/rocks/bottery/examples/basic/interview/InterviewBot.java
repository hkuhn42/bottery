/**
 * Copyright (C) 2016-2018 Harald Kuhn
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.examples.basic.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Decision;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.bot.dialogs.IValidator;
import rocks.bottery.bot.dialogs.Interview;
import rocks.bottery.bot.dialogs.Question;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.recognizers.CommandRecognizer;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;
import rocks.bottery.bot.util.SessionPropertyModel;
import rocks.bottery.connector.console.ConsoleConnector;;

/**
 * An example of a simple scripted bot without using a recognizer
 * 
 * @author Harald Kuhn
 *
 */
public class InterviewBot extends UniversalBot {

	public InterviewBot() {
		List<Choice<String>> choices = new ArrayList<>();
		choices.add(new Choice<String>("Java", "Java"));
		choices.add(new Choice<String>("Groovy", "Groovy"));
		choices.add(new Choice<String>("Scala", "Scala"));

		IDialog[] iDialogs = new IDialog[] {

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

		};
		try {
			addRecognizer(new CommandRecognizer("interview"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setWelcomeDialog(new Utterance("Hi, wanna answer some questions?"));

		addDialog("interview", new Interview<DeveloperStatsBean>("statsBean", DeveloperStatsBean.class, iDialogs) {
			@Override
			protected void interviewFinished(ISession session, IActivity last, DeveloperStatsBean resultBean) {
			}
		});
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		// MSConnector connector = new MSConnector();
		ConsoleConnector connector = new ConsoleConnector();
		InterviewBot bot = new InterviewBot();
		// bot.getBotConfig().getLocalizer().getString(new Locale("EN"), "", null);
		connector.init(bot.getBotConfig());
		connector.register(bot);

	}

}

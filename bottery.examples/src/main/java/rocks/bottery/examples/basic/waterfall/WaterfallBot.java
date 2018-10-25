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
package rocks.bottery.examples.basic.waterfall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Decision;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.bot.dialogs.Interview;
import rocks.bottery.bot.dialogs.Question;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;
import rocks.bottery.bot.util.SessionModel;
import rocks.bottery.connector.IConnector;
import rocks.bottery.connector.console.ConsoleConnector;;

/**
 * @author Harald Kuhn
 *
 */
public class WaterfallBot extends UniversalBot {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		WaterfallBot bot = new WaterfallBot();

		bot.initDialogs();
		// new ConsoleConnector().register(bot);
		// new MSConnector().register(bot);
		IConnector discordConnector = new ConsoleConnector();
		discordConnector.init(bot.getBotConfig());
		discordConnector.register(bot);
	}

	protected void initDialogs() {
		List<Choice<String>> choices = new ArrayList<>();
		choices.add(new Choice<String>("Java", "Java"));
		choices.add(new Choice<String>("Groovy", "Groovy"));
		choices.add(new Choice<String>("Scala", "Scala"));

		// String[] choices = new String[] { "Java", "Groovy", "Scala" };

		setWelcomeDialog(new Interview(new IDialog[] {

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

		}) {

			@Override
			protected void interviewFinished(ISession session, Serializable resultBean) {
				// TODO Auto-generated method stub

			}
		});
	}

}

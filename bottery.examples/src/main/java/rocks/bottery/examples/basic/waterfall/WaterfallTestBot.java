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

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.bot.dialogs.Interview;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.Model;
import rocks.bottery.connector.bot2bot.Bot2BotConnector;;

/**
 * @author Harald Kuhn
 *
 */
public class WaterfallTestBot extends UniversalBot {

	public static void main(String[] args) {
		// BasicConfigurator.configure();

		WaterfallTestBot otherBot = new WaterfallTestBot();
		otherBot.initDialogs();

		WaterfallBot bot = new WaterfallBot();
		bot.initDialogs();

		new Bot2BotConnector(otherBot).register(bot);
	}

	private void initDialogs() {

		setWelcomeDialog(new Interview(new IDialog[] {

		        new Utterance(new Model<>("Harald")), new Utterance(new Model<>("12")), new Utterance(new Model<>("Java")), new IDialog() {

			        @Override
			        public void handle(ISession session, IActivity activity) {
				        if (activity.getText().equalsIgnoreCase("Got it... Harald you've been programming for 12 years and use Java.")) {
					        System.out.println("ok");
					        System.exit(0);
				        }
				        else {
					        new RuntimeException("invalid answer").printStackTrace();
					        ;
					        System.exit(0);
				        }
			        }
		        }

		}) {
			@Override
			protected void interviewFinished(ISession session, IActivity last, Serializable resultBean) {

			}
		});
	}

}

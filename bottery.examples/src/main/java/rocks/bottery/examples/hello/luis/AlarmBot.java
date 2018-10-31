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
package rocks.bottery.examples.hello.luis;

import java.io.IOException;

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
		// set a welcome dialog - this is not necessary if the recognizer is set to greedy as it always jumps in but its
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
		try {
			addRecognizer(recognizer);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

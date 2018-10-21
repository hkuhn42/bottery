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
package rocks.bottery.connector.console;

import java.util.Scanner;

import org.apache.log4j.Logger;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericActivity;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.messaging.IReceiver;

/**
 * Connector for development which routes console input and output to and from the bots
 * 
 * Support some simple commands to simulate advanced featues
 * <table summary="list of supported commands">
 * <tr>
 * <td>/kill</td>
 * <td>end connector thread ending the</td>
 * </tr>
 * </table>
 * 
 * 
 * @author Harald Kuhn
 */
public class ConsoleConnector extends ExecutorConnectorBase {

	private IActivity lastMessage;

	@Override
	public void register(final IReceiver handler) {
		executor.submit(new Runnable() {

			private Scanner scanner = new Scanner(System.in);

			@Override
			public void run() {
				GenericActivity activity = newMessageTo(new GenericParticipant("bot", "bot", getChannel(), "bot@bot"));
				activity.setType(ActivityType.START);
				handler.receive(activity, ConsoleConnector.this);
				listen(handler);
			}

			public void listen(final IReceiver handler) {
				// get the text
				String text = scanner.nextLine();

				// prepare activity
				GenericActivity activity = null;
				if (lastMessage != null) {
					activity = newReplyTo(lastMessage);
				}
				else {
					activity = newMessageTo(new GenericParticipant("bot", "bot", getChannel(), "bot@bot"));
				}

				// handle special commands
				if ("/sendFile".equalsIgnoreCase(text)) {
					// load file
				}
				else if ("/typing".equalsIgnoreCase(text)) {
					// load file
					activity.setType(ActivityType.OTHER);
				}
				else if ("/kill".equalsIgnoreCase(text)) {
					executor.shutdown();
					return;
				}
				else {
					// else its a text message
					activity.setText(text);
				}

				try {
					Logger.getLogger(ConsoleConnector.class).info("send activity " + activity + " to handler " + handler);
					handler.receive(activity, ConsoleConnector.this);
				}
				catch (Exception e) {
					// printStackTrace is the right choice here as it writes to system.err
					e.printStackTrace();
				}
				listen(handler);
			}
		});
	}

	@Override
	public void send(final IActivity response) {
		lastMessage = response;
		executor.submit(new Runnable() {

			@Override
			public void run() {
				StringBuilder builder = new StringBuilder();
				builder.append(response.getText());
				if (response.getChoices() != null && response.getChoices().size() > 0) {
					builder.append("\n");
					for (int i = 0; i < response.getChoices().size(); i++) {
						builder.append((i + 1) + ": ");
						builder.append(response.getChoices().get(i).getLabelString());
						builder.append("\n");
					}
				}

				System.out.println(builder.toString());
			}

		});
	}

	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("console", "console", getChannel(), "console@bot");
	}

	@Override
	public String getChannel() {
		return "console";
	}
}

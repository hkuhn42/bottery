/**
 * Copyright (C) 2016-2017 Harald Kuhn
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

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.messaging.IReceiver;

/**
 * Connector for development which routes console input and output to and from the bots
 * 
 * @author Harald Kuhn
 *
 */
public class ConsoleConnector extends ExecutorConnectorBase {

	@Override
	public void register(final IReceiver handler) {
		// Thread t = new Thread(new Runnable() {
		executor.submit(new Runnable() {

			private Scanner scanner = new Scanner(System.in);

			@Override
			public void run() {
				listen(handler);
			}

			public void listen(final IReceiver handler) {
				String text = scanner.nextLine();

				IActivity activity = newMessageTo(new GenericParticipant("shell", "shell", "shell"));
				activity.setText(text);

				handler.receive(activity, ConsoleConnector.this);
				listen(handler);
			}
		});
	}

	@Override
	public void send(final IActivity response) {
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
		return new GenericParticipant("shell", "shell", "shell");
	}

	@Override
	public String getChannel() {
		return "console";
	}
}

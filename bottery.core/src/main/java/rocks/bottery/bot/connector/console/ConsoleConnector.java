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
package rocks.bottery.bot.connector.console;

import java.util.Scanner;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.GenericParticipant;

/**
 * Connector for development which routes console input and output to and from the bots
 * 
 * @author Harald Kuhn
 *
 */
public class ConsoleConnector extends ExecutorConnectorBase {

	@Override
	public void listen(final IBot handler) {
		// Thread t = new Thread(new Runnable() {
		executor.submit(new Runnable() {

			private Scanner scanner = new Scanner(System.in);

			@Override
			public void run() {
				listen(handler);
			}

			public void listen(final IBot handler) {
				String text = scanner.nextLine();

				IActivity activity = newMessageTo(new GenericParticipant("shell", "shell"));
				activity.setText(text);

				handler.receive(activity);
				listen(handler);
			}
		});
		// t.start();

	}

	@Override
	public void send(final IActivity data) {
		executor.submit(new Runnable() {
			// Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(data.getText());
			}

		});
		// t.start();
	}

	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("shell", "shell");
	}

	@Override
	protected String getChannel() {
		return "console";
	}
}

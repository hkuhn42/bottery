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
package rocks.bottery.examples.hello.telegram;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.connector.TelegramBotConnector;
import rocks.bottery.examples.AttaBot;;

/**
 * This example uses the msConnector for a hello world
 */
public class HelloTelegram {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new TelegramBotConnector("telegram").register(new AttaBot());
	}
}

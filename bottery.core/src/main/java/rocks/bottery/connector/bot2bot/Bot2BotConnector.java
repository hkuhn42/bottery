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
package rocks.bottery.connector.bot2bot;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;

/**
 * This connector allows to let two Bots chat with each other.
 * 
 * The most obvious reason for this is to test a bot with anaother bot.
 * 
 * The connector is created with the second bot as a constructor.. The first or primary bot is created normally and
 * attached to the connector via the listen method
 * 
 * E.g. (shortest possible varaint)
 * 
 * <pre>
 * new Bot2BotConnector(new TestBot()).listen(new BotToTest());
 * </pre>
 * 
 *
 * @author Harald Kuhn
 */
public class Bot2BotConnector extends ConnectorBase {

	private IBot		 otherBot;

	private IBot		 mainBot;

	private SubConnector subConnector;

	public Bot2BotConnector(IBot otherBot) {
		this.otherBot = otherBot;
		subConnector = new SubConnector();
	}

	@Override
	public void register(IBot mainBot) {
		this.mainBot = mainBot;
		IActivity newMessageTo = newMessageTo(new GenericParticipant("bot", "bot", "bot2bot"));
		newMessageTo.setType(ActivityType.MESSAGE);
		newMessageTo.setText("Hi");
		mainBot.receive(newMessageTo, this);
	}

	@Override
	public void shutdown() {
		otherBot = null;
		mainBot = null;
		subConnector = null;
	}

	@Override
	public void send(IActivity activity) {
		System.out.println("> " + activity.getText());
		otherBot.receive(activity, subConnector);
	}

	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("dummy", "dummy", "bot2bot");
	}

	@Override
	public String getChannel() {
		return "Bot2Bot";
	}

	class SubConnector extends ConnectorBase {

		@Override
		public void register(IBot handler) {

		}

		@Override
		public void send(IActivity activity) {
			System.out.println("< " + activity.getText());
			mainBot.receive(activity, Bot2BotConnector.this);
		}

		@Override
		public IParticipant getConnectorAccount() {
			return Bot2BotConnector.this.getConnectorAccount();
		}

		@Override
		public String getChannel() {
			return Bot2BotConnector.this.getChannel();
		}

	}
}

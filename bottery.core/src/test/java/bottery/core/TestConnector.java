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
package bottery.core;

import java.util.Stack;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.GenericParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.messaging.IReceiver;

/**
 * @author Harald Kuhn
 *
 */
public class TestConnector extends ConnectorBase {

	private Stack<IActivity> testActivities;
	private IReceiver		 bot;

	public TestConnector(Stack<IActivity> testActivities) {
		this.testActivities = testActivities;
		bot.receive(testActivities.pop(), this);
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public void register(IReceiver receiver) {
		this.bot = receiver;
		receiver.receive(testActivities.pop(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#shutdown()
	 */
	@Override
	public void shutdown() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#send(rocks.bottery.bot.IActivity)
	 */
	@Override
	public void send(IActivity data) {
		System.out.println("got: " + data.getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IConnector#getConnectorAccount()
	 */
	@Override
	public IParticipant getConnectorAccount() {
		return new GenericParticipant("1", "Chuck", "test");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.connector.console.ConnectorBase#getChannel()
	 */
	@Override
	public String getChannel() {
		return "test";
	}

}

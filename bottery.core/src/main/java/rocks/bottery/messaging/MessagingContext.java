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
package rocks.bottery.messaging;

import rocks.bottery.bot.ContextBase;
import rocks.bottery.notifications.ITrigger;

/**
 * Basic implementation of IMessagingContext (incomplete)
 * 
 * @author Harald Kuhn
 */
public class MessagingContext extends ContextBase implements IMessagingContext {

	private IConnectorRegistry registry;

	public MessagingContext() {
		registry = new ConnectorRegistry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.messaging.IMessagingContext#getConnectorRegistry()
	 */
	@Override
	public IConnectorRegistry getConnectorRegistry() {
		return registry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.messaging.IMessagingContext#schedule(rocks.bottery.notifications.INotifier,
	 * rocks.bottery.notifications.ITrigger)
	 */
	@Override
	public void register(ITrigger trigger) {

	}

}

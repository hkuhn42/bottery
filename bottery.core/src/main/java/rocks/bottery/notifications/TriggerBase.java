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
package rocks.bottery.notifications;

import java.util.ArrayList;
import java.util.List;

import rocks.bottery.messaging.IMessagingContext;

/**
 * Base class for a ITrigger which stores the IMessagingContext and keeps track of registered notifiers
 * 
 * @author Harald Kuhn
 */
public abstract class TriggerBase implements ITrigger {

	protected List<INotifier>	notifiers;
	protected IMessagingContext	context;

	@Override
	public void register(INotifier notifier) {
		if (notifiers == null) {
			notifiers = new ArrayList<>();
		}
		notifiers.add(notifier);
	}

	@Override
	public void start(IMessagingContext context) {
		this.context = context;
		startInternal();
	}

	/**
	 * Start trigger for subclasses
	 */
	protected abstract void startInternal();

}

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
package rocks.bottery.notifications;

import rocks.bottery.messaging.IMessagingContext;

/**
 * Marker interface for notifier triggers
 * 
 * @author Harald Kuhn
 */
public interface ITrigger {

	/**
	 * insturcts the trigger to start this notifier
	 * 
	 * @param notifier
	 */
	public void register(INotifier notifier);

	public void start(IMessagingContext context);
}

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
package rocks.bottery.notifications;

import rocks.bottery.messaging.IMessagingContext;

/**
 * Can be used to send activities to recipients based on arbital triggers (anything but a recent message) e.g. Timers or
 * Events
 * 
 * A notifier should normally be called by a trigger and will then send activities to any of the connectors in the
 * message registry
 * 
 * @author Harald Kuhn
 *
 */
public interface INotifier {

	/**
	 * Called by a trigger to activate this notifier.
	 * 
	 * @param trigger
	 *            the trigger calling notify
	 * @param context
	 *            the messaging context for sending activities
	 */
	public void notify(ITrigger trigger, IMessagingContext context);

}
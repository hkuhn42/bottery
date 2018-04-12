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

import rocks.bottery.connector.IConnector;

/**
 * Defines a registry for different connectors
 * 
 * @author Harald Kuhn
 */
public interface IConnectorRegistry {

	/**
	 * Register a connector
	 * 
	 * @param connector
	 *            the connector to register
	 */
	public void register(IConnector connector);

	/**
	 * Find a connector by channel
	 * 
	 * @param channel
	 *            the name / id of the channel
	 * @return the connector registered for this channel
	 */
	public IConnector get(String channel);

}

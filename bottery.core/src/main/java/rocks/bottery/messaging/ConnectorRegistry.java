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

import java.util.HashMap;
import java.util.Map;

import rocks.bottery.connector.IConnector;

/**
 * Basic implementation of a connector registry
 * 
 * @author Harald Kuhn
 *
 */
public class ConnectorRegistry implements IConnectorRegistry {

	private Map<String, IConnector> connectors;

	public ConnectorRegistry() {
		connectors = new HashMap<>();
	}

	@Override
	public void register(IConnector connector) {
		connectors.put(connector.getChannel(), connector);
	}

	@Override
	public IConnector get(String channel) {
		return connectors.get(channel);
	}

}

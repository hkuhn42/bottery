/**
 * 
 */
package rocks.bottery.messaging;

import rocks.bottery.connector.IConnector;

/**
 * @author Harald Kuhn
 *
 */
public interface IConnectorRegistry {

	public void register(IConnector connector);

	public IConnector get(String channel);

}

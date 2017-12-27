/**
 * 
 */
package rocks.bottery.messaging;

import rocks.bottery.bot.IContext;
import rocks.bottery.notifications.INotifier;
import rocks.bottery.notifications.ITrigger;

/**
 * @author Harald Kuhn
 *
 */
public interface IMessagingContext extends IContext {

	public IConnectorRegistry getConnectorRegistry();

	public void schedule(INotifier notifier, ITrigger trigger);

}

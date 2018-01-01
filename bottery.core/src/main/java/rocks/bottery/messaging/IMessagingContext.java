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

	/**
	 * Gives access to all connectors in this messaging context
	 * 
	 * @return
	 */
	public IConnectorRegistry getConnectorRegistry();

	/**
	 * add a notifier with its trigger to this context. The context does only keep the references. This enables the bot
	 * and dialogs (or whatever creates the notifier and trigger) to be stateless and still "keep" the notifier
	 * 
	 * @param notifier
	 * @param trigger
	 */
	public void schedule(INotifier notifier, ITrigger trigger);

}

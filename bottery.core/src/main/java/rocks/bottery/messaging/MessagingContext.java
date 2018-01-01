/**
 * 
 */
package rocks.bottery.messaging;

import rocks.bottery.bot.ContextBase;
import rocks.bottery.notifications.INotifier;
import rocks.bottery.notifications.ITrigger;

/**
 * @author Harald Kuhn
 *
 */
public class MessagingContext extends ContextBase implements IMessagingContext {

	private IConnectorRegistry registry;

	public MessagingContext() {

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
	public void schedule(INotifier notifier, ITrigger trigger) {

	}

}

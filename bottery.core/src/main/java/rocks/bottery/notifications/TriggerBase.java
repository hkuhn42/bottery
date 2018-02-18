/**
 * 
 */
package rocks.bottery.notifications;

import java.util.ArrayList;
import java.util.List;

import rocks.bottery.messaging.IMessagingContext;

/**
 * @author Harald Kuhn
 *
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

	}

	/**
	 * start trigger
	 */
	protected abstract void startInternal();

}

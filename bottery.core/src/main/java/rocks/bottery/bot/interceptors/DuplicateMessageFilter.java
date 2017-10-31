/**
 * 
 */
package rocks.bottery.bot.interceptors;

import org.apache.log4j.Logger;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IActivityArchive;
import rocks.bottery.bot.ISession;

/**
 * @author Harald Kuhn
 *
 */
public class DuplicateMessageFilter extends InterceptorBase implements IInterceptor {

	private IActivityArchive archive;

	public DuplicateMessageFilter(IActivityArchive archive) {
		this.archive = archive;
		super.nextInChain = archive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IHandler#handle(rocks.bottery.bot.ISession, rocks.bottery.bot.IActivity)
	 */
	@Override
	public void handle(ISession session, IActivity activity) {
		IActivity archivedActivity = archive.getActivityById(activity.getId());
		if (archivedActivity == null) {
			nextInChain.handle(session, activity);
		}
		else {
			Logger.getLogger(DuplicateMessageFilter.class).debug("surpressed duplicate " + activity.getId());

		}
	}

}

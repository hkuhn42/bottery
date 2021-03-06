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

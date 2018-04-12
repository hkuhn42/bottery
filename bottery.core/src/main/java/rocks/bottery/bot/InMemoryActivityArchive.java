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
package rocks.bottery.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import rocks.bottery.bot.interceptors.InterceptorBase;

/**
 * @author Harald Kuhn
 *
 */
public class InMemoryActivityArchive extends InterceptorBase implements IActivityArchive {

	private Map<String, List<IActivity>> conversations;

	public InMemoryActivityArchive() {
		conversations = new HashMap<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IHandler#handle(org.sylvani.bot.ISession, org.sylvani.bot.IActivity)
	 */
	@Override
	public void handle(ISession session, IActivity activity) {
		List<IActivity> activities = conversations.get(session.getId());
		if (activities == null) {
			activities = new ArrayList<>();
			conversations.put(session.getId(), activities);
		}
		activities.add(activity);
		Logger.getLogger(InMemoryActivityArchive.class).info("archived " + activity.getId() + " " + activity.getType() + " " + activity.getText());
		nextInChain.handle(session, activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivityArchive#removeConversation(java.lang.String)
	 */
	@Override
	public void removeConversation(String id) {
		conversations.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IActivityArchive#getMessagesByConversation(java.lang.String)
	 */
	@Override
	public List<IActivity> getActivitiesByConversation(String id) {
		return conversations.get(id);
	}

	@Override
	public IActivity getActivityById(String id) {
		for (List<IActivity> conv : conversations.values()) {
			for (IActivity activity : conv) {
				if (id.equalsIgnoreCase(activity.getId())) {
					return activity;
				}
			}
		}
		return null;
	}
}

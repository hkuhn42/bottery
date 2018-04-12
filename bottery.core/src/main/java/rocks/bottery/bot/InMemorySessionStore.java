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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harald Kuhn
 *
 */
public class InMemorySessionStore implements ISessionStore {

	private Map<String, ISession> conversations;

	public InMemorySessionStore() {
		this.conversations = new HashMap<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.ISessionStore#find(java.lang.String)
	 */
	@Override
	public ISession find(String id) {
		return conversations.get(id);
	}

	@Override
	public void add(String convId, ISession session) {
		conversations.put(convId, session);
	}

	@Override
	public void remove(ISession session) {
		conversations.remove(session.getId());
	}
}

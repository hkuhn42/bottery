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
package rocks.bottery.bot.util;

import java.io.Serializable;

import rocks.bottery.bot.ISession;

/**
 * @author hkuhn
 */
public class SessionModel<T extends Serializable> implements ISessionModel<T> {

	private static final long serialVersionUID = 1L;

	private String			  name;

	public SessionModel(String name) {
		this.name = name;
	}

	@Override
	public void setObject(T object, ISession session) {
		session.setAttribute(name, object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObject(ISession session) {
		return (T) session.getAttribute(name);
	}
}

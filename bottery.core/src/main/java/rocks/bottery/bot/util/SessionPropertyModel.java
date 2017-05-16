/**
 * Copyright (C) 2016-2017 Harald Kuhn
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
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

import rocks.bottery.bot.ISession;

/**
 * @author hkuhn
 */
public class SessionPropertyModel<T extends Serializable> implements ISessionModel<T> {

	private static final long serialVersionUID = 1L;

	private String			  beanName;
	private String			  propertyName;

	public SessionPropertyModel(String beanName, String propertyName) {
		this.beanName = beanName;
		this.propertyName = propertyName;
	}

	@Override
	public void setObject(T object, ISession session) {
		Object target = session.getAttribute(beanName);
		try {
			PropertyUtils.setProperty(target, propertyName, object);
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public T getObject(ISession session) {
		Object target = session.getAttribute(beanName);
		try {
			return (T) PropertyUtils.getProperty(target, beanName);
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

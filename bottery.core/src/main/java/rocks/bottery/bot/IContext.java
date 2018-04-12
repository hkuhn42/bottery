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
package rocks.bottery.bot;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * General purpose context
 * 
 * @author Harald Kuhn
 */
public interface IContext {

	/**
	 * fetch a context attribute
	 * 
	 * @param name
	 *            the name of the attribute
	 * @return the attribute or null
	 */
	public Serializable getAttribute(String name);

	/**
	 * Get all attributes as an iterator
	 * 
	 * @return an iterator of all attributes
	 */
	public Set<String> getAttributeNames();

	/**
	 * Set an attribute in the context
	 * 
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, Serializable value);

	/**
	 * remove the attribute
	 * 
	 * @param name
	 * @return
	 */
	public Serializable removeAttribut(String name);

	/**
	 * get attributes as immutable map
	 * 
	 * @return
	 */
	public Map<String, Serializable> asAttributeMap();

}

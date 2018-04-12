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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.globalmentor.collections.Maps;

/**
 * General purpose context base impl.
 * 
 * @author hkuhn
 */
public abstract class ContextBase implements IContext {

	private Map<String, Serializable> attributes;

	public ContextBase() {
		attributes = new HashMap<>();

	}

	@Override
	public Serializable getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	@Override
	public void setAttribute(String name, Serializable value) {
		attributes.put(name, value);
	}

	@Override
	public Serializable removeAttribut(String name) {
		return attributes.remove(name);
	}

	@Override
	public Map<String, Serializable> asAttributeMap() {
		return Maps.toImmutableMap(attributes);
	}

}

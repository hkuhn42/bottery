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
package rocks.bottery.bot.recognizers;

import rocks.bottery.bot.ContextBase;

/**
 * General purpose implementation of an IIntent Entity
 * 
 * @author Harald Kuhn
 */
public class Entity extends ContextBase implements IEntity {

	private static final long serialVersionUID = 1L;

	private String			  name;
	private String			  value;

	public Entity(String name) {
		super();
		this.name = name;
	}

	public Entity(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IEntity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}

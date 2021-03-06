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

import java.io.Serializable;

import rocks.bottery.bot.IContext;

/**
 * Defines an Entity in an IIntent
 * 
 * @author Harald Kuhn
 */
public interface IEntity extends IContext, Serializable {

	/**
	 * Get the name of the entity
	 * 
	 * @return the name
	 */
	String getName();

}

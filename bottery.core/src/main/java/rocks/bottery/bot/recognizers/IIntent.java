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
import java.util.List;

import rocks.bottery.bot.IContext;

/**
 * The intent of the user (ranges from a simple commant extracted from the text to a complex intent with attributes
 * 
 * @author Harald Kuhn
 */
public interface IIntent extends IContext, Serializable {

	/**
	 * get the name of the intent
	 */
	String getIntentName();

	/**
	 * get a suggestion for a response to the user based in the detected intent, may be null
	 * 
	 * @return response suggestion or null if this intent does not contain a respose suggestion
	 */
	String getResponseSuggestion();

	/**
	 * get the underlying intent object (api specific)
	 */
	Object getRecognizerIntent();

	/**
	 * Get the detection confidence
	 * 
	 * @return
	 */
	double getConfidence();

	/**
	 * get the entities of this Intent
	 * 
	 * @return
	 */
	List<IEntity> getEntities();

	/**
	 * get the entity with the given nae
	 * 
	 * @param name
	 * @return
	 */
	IEntity getEntity(String name);
}

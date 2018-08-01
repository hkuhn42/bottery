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

/**
 * Utility API for resolving variables in text
 * 
 * @author Harald Kuhn
 */
public interface IVariableResolver {
	/**
	 * resolve variables from the given context in the input text
	 * 
	 * @param text
	 *            the input text
	 * @param context
	 *            the context containing the variables
	 * @return the text with variables resolved
	 */
	String resolveVariables(String text, IContext context);

	/**
	 * resolve variables from the given context in the input text
	 * 
	 * @param text
	 *            the input text
	 * @param variables
	 *            a map containing the variables
	 * @return the text with variables resolved
	 */
	String resolveVariables(String text, Map<String, Serializable> variables);
}
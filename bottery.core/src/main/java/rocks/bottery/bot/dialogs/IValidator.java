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
package rocks.bottery.bot.dialogs;

import rocks.bottery.bot.ISession;

/**
 * Used to validate answers
 * 
 * @author Harald Kuhn
 */
public interface IValidator<T> {

	/**
	 * validate an answer by either the converted answer or the "raw" text
	 * 
	 * @param answer
	 *            of type T
	 * @param answerText
	 *            the text of the Answer
	 * @param session
	 *            the session
	 * @return a ValidationResult
	 */
	public ValidationResult validate(T answer, String answerText, ISession session);

}

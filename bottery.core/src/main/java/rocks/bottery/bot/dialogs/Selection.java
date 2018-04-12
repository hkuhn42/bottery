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
package rocks.bottery.bot.dialogs;

import java.util.List;

import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.util.Data;
import rocks.bottery.bot.util.ISessionModel;
import rocks.bottery.connector.GenericActivity;

/**
 * Selection of choices
 * 
 * @author Harald Kuhn
 *
 */
public abstract class Selection<T> extends Question<T> {

	private List<Choice<T>> choices;

	public Selection(ISessionModel<T> anwerModel, List<Choice<T>> choices) {
		super(anwerModel);
		this.choices = choices;
	}

	@Override
	protected T findAnswer(String text, IActivity answerActivity, ISession session) throws InvalidAnswerException {
		String answer = null;
		if (text.contains("add")) {
			try {
				answer = text.substring(0, text.indexOf("add"));
			}
			catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		for (Choice<T> choice : choices) {
			String choiceString = choice.getLabel().getObject();
			if (answer.indexOf(choiceString) >= 0) {
				return choice.getValue();
			}
		}
		return null;
	}

	@Override
	protected void fillActivity(IActivity request, GenericActivity response, ISession session) {
		// FIXME: add 1. 2. 3. etc to list and also use for answer resolving
		super.fillActivity(response, response, session);
		response.setText(response.getText() + "\n" + Data.join(", ", choices));
	}
}

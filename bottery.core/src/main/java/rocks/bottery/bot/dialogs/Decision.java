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
package rocks.bottery.bot.dialogs;

import java.util.ArrayList;
import java.util.List;

import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.ISessionModel;
import rocks.bottery.bot.util.Model;
import rocks.bottery.connector.GenericActivity;

/**
 * A decision is a {@link Question} with a coice of possible answers
 * 
 * @author Harald Kuhn
 *
 */
public abstract class Decision<T> extends Question<T> {

	private IModel<List<Choice<T>>> choicesModel;

	public Decision(ISessionModel<T> anwerModel, IModel<List<Choice<T>>> choicesModel) {
		super(anwerModel);
		this.choicesModel = choicesModel;
	}

	public Decision(ISessionModel<T> anwerModel, List<Choice<T>> choices) {
		super(anwerModel);
		this.choicesModel = new Model<List<Choice<T>>>(choices);
	}

	@Override
	protected T findAnswer(String text, IActivity answerActivity, ISession session) throws InvalidAnswerException {
		if (text == null) {
			return null;
		}
		String answer = text.trim();
		List<Choice<T>> choices = choicesModel.getObject();
		for (int i = 0; i < choices.size(); i++) {
			if (answer.indexOf(String.valueOf(i + 1)) >= 0) {
				return choices.get(i).getValue();
			}
			String choiceString = choices.get(i).getLabel().getObject();
			if (answer.indexOf(choiceString) >= 0) {
				return choices.get(i).getValue();
			}
		}
		return null;
	}

	// public IModel<String> getConfirmText(Choice<?> choice) {
	// return new Model<>("OK, added ");
	// }

	@Override
	protected void fillActivity(IActivity request, GenericActivity response, ISession session) {
		super.fillActivity(request, response, session);
		List<Choice<?>> requestChoices = new ArrayList<Choice<?>>();
		for (Choice<T> choice : choicesModel.getObject()) {
			requestChoices.add(choice);
		}
		response.setChoices(requestChoices);
	}
}

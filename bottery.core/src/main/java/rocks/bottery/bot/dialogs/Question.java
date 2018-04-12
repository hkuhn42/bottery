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

import java.lang.reflect.ParameterizedType;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.ISessionModel;
import rocks.bottery.connector.GenericActivity;

/**
 * A simple question
 * 
 * It has a simple two state design initially giving the question and then expecting an answer which is stored via a
 * session model. The questions text is retrieved from a model via getText. The answer will be converted to the
 * answerModesl Data type and can be validated by a custom validator.
 * 
 * It acts similar to a field in a form with a label and an input.
 * 
 * @author Harald Kuhn
 *
 */
public abstract class Question<T> extends DialogBase {

	private Logger			 logger	= LoggerFactory.getLogger(Question.class);

	private IValidator<T>	 answerValidator;
	private ISessionModel<T> answerModel;

	private IModel<String>	 confirmText;
	/**
	 * a key for looking up an answer from an intents attributes
	 */
	private String			 answerIntentKey;

	// the key used to store the state of this question in the session
	private String			 instanceStateKey;

	/**
	 * Creates a new Question
	 * 
	 * @param anwerModel
	 *            the model to store the answer in
	 */
	public Question(ISessionModel<T> anwerModel) {
		this.answerModel = anwerModel;
		instanceStateKey = String.valueOf(this.hashCode());
	}

	/**
	 * use only for subclassing when {@link #answered(Object, IActivity, ISession)} is overwritten
	 */
	protected Question() {
		instanceStateKey = String.valueOf(this.hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IHandler#handle(org.sylvani.bot.ISession, org.sylvani.bot.IActivity)
	 */
	@Override
	public void handle(ISession session, IActivity activity) {
		T answer = null;
		// check wether we have asked for this session
		Object alreadyAsked = session.getAttribute(instanceStateKey);
		logger.debug("asked:  " + instanceStateKey + "  = " + alreadyAsked);
		if (alreadyAsked == null) {
			// and remember in the stream
			session.setAttribute(instanceStateKey, "asked");
			// if not call super (utterance will output the question)
			super.handle(session, activity);
		}
		else {
			String text = activity.getText();
			try {
				answer = findAnswer(text, activity, session);
			}
			catch (InvalidAnswerException e) {
				session.send(e.getFeedback());
			}

			if (answer != null) {
				IModel<String> confirmTextModel = getConfirmText();
				if (confirmTextModel != null) {
					GenericActivity confirm = session.getConnector().newReplyTo(activity);
					confirm.setText(confirmTextModel.getObject());
					session.send(confirm);
				}
				answered(answer, activity, session);
				return;
			}

		}
	}

	/**
	 * Callback if the response to the question is detected
	 * 
	 * @param answer
	 * @param answerActivity
	 * @param session
	 */
	protected void answered(T answer, IActivity answerActivity, ISession session) {
		session.removeAttribut(instanceStateKey);
		answerModel.setObject(answer, session);
		// send the current text on to the next dialog to "trigger" it
		session.activeDialogFinished(answerActivity);
	}

	/**
	 * Tries to find the answer either in the response text or in the detected intends attributes
	 * 
	 * @param answerText
	 * @param answerActivity
	 * @param session
	 * @return
	 * @throws InvalidAnswerException
	 */
	protected T findAnswer(String answerText, IActivity answerActivity, ISession session) throws InvalidAnswerException {
		T answer = null;
		if (answerActivity.getIntent() != null && answerActivity.getIntent().getAttribute(answerIntentKey) != null) {
			answer = convert(String.valueOf(answerActivity.getIntent().getAttribute(answerIntentKey)));
		}
		else {
			answer = convert(answerText);
		}
		if (answerValidator != null) {
			ValidationResult result = answerValidator.validate(answer, answerText, session);
			if (!result.isValid()) {
				IActivity feedback = session.getConnector().newReplyTo(answerActivity);

				throw new InvalidAnswerException(feedback);
			}
		}
		return answer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected T convert(String answerText) {
		Class clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		return (T) ConvertUtils.convert(answerText, clazz);
	}

	public IValidator<T> getAnswerValidator() {
		return answerValidator;
	}

	/**
	 * Set a customm answer validator
	 * 
	 * @param answerValidator
	 */
	public void setAnswerValidator(IValidator<T> answerValidator) {
		this.answerValidator = answerValidator;
	}

	/**
	 * Get the confirm text
	 * 
	 * @return
	 */
	public IModel<String> getConfirmText() {
		return confirmText;
	}

	public void setConfirmText(IModel<String> confirmText) {
		this.confirmText = confirmText;
	}

	public String getAnswerIntentKey() {
		return answerIntentKey;
	}

	public void setAnswerIntentKey(String answerIntentKey) {
		this.answerIntentKey = answerIntentKey;
	}

}

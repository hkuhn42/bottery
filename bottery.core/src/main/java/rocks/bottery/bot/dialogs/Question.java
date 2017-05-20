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

import java.lang.reflect.ParameterizedType;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.ISessionModel;

/**
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

	public Question(ISessionModel<T> anwerModel) {
		this.answerModel = anwerModel;
	}

	/**
	 * use only for subclassing when {@link #answered(Object, ISession)} is overwritten
	 */
	protected Question() {
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
		Object alreadyAsked = session.getAttribute(String.valueOf(this.hashCode()));
		logger.debug("asked code =  " + alreadyAsked);
		if (alreadyAsked != null) {
			String text = activity.getText();
			try {
				answer = findAnswer(text, activity, session);
			}
			catch (InvalidAnswerException e) {
				session.send(e.getFeedback());
			}

			if (answer != null) {
				answered(answer, activity, session);
				return;
			}
		}
		else {
			// if not call super (utterance will output the question)
			super.handle(session, activity);
			// and remember in the stream
			session.setAttribute(String.valueOf(this.hashCode()), "asked");
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
		session.removeAttribut("asked");
		answerModel.setObject(answer, session);
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

	public void setAnswerValidator(IValidator<T> answerValidator) {
		this.answerValidator = answerValidator;
	}

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

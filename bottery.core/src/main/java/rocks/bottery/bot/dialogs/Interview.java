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
package rocks.bottery.bot.dialogs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;

/**
 * Simple waterfall dialog which consists of a number of subdialogs which are processed in sequence
 * 
 * The inverview keeps track of the index of the current subdialog and sets the next one as activeDialog each time the
 * previous one calls finished
 * 
 * @author Harald Kuhn
 */
public abstract class Interview<T extends Serializable> implements IDialog {

	private static final String				DIALOG_STATE_PREFIX	= "Interview";

	protected List<IDialog>					dialogs;
	protected String						instanceStateKey;
	protected String						resultBeanName;
	protected Class<? extends Serializable>	resultBeanClazz;

	/**
	 * Instantiate a new Inteview
	 * 
	 * 
	 * @param resultBeanName
	 *            the name of the result bean in the session
	 * @param resultBeanClazz
	 *            the class of the result bean
	 * @param dialogs
	 *            an array of dialogs executed in that order
	 */
	public Interview(String resultBeanName, Class<? extends Serializable> resultBeanClazz, IDialog[] dialogs) {
		this(dialogs);
		this.resultBeanClazz = resultBeanClazz;
		this.resultBeanName = resultBeanName;
	}

	public Interview() {
		this.dialogs = new ArrayList<>();
		this.instanceStateKey = DIALOG_STATE_PREFIX + "." + UUID.randomUUID().toString();
	}

	public Interview(IDialog[] dialogs) {
		this();
		this.dialogs.addAll(Arrays.asList(dialogs));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani. bot.IHandler#handle(org.sylvani.bot.ISession, org.sylvani.bot.connector.ms.model.Activity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handle(ISession session, IActivity activity) {
		if (resultBeanClazz != null && session.getAttribute(resultBeanName) == null) {
			try {
				T resultBean = initBean(session, activity);
				session.setAttribute(resultBeanName, resultBean);
			}
			catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		Integer instanceState = (Integer) session.getAttribute(instanceStateKey);
		if (instanceState == null) {
			instanceState = new Integer(0);
		}
		else if (instanceState >= dialogs.size()) {
			session.activeDialogFinished();
			session.removeAttribut(instanceStateKey);
			interviewFinished(session, activity, (T) session.getAttribute(resultBeanName));
			return;
		}
		IDialog activeDialog = dialogs.get(instanceState.intValue());
		session.setActiveDialog(activeDialog);
		instanceState++;
		session.setAttribute(instanceStateKey, instanceState);

		activeDialog.handle(session, activity);
	}

	// called with the resuslt bean after the inreview finished
	protected abstract void interviewFinished(ISession session, IActivity last, T resultBean);

	/**
	 * initializes a new result bean
	 * 
	 * @param session
	 *            TODO
	 * @param start
	 *            TODO
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected T initBean(ISession session, IActivity start) throws InstantiationException, IllegalAccessException {
		return (T) resultBeanClazz.newInstance();
	}
}
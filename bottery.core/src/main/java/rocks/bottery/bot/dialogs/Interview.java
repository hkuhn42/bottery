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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;

/**
 * Simple waterfall dialog which consists of a number of subdialogs which are processes in sequence
 * 
 * The inverview keeps track of the index of the current subdialog and sets the next one as activeDialog each time the
 * previous one calls finished
 * 
 * @author Harald Kuhn
 */
public class Interview implements IDialog {

	private static final String			  DIALOG_STATE_PREFIX = "Interview";

	protected List<IDialog>				  dialogs;
	private String						  instanceStateKey;
	private String						  resultBeanName;
	private Class<? extends Serializable> resultBeanClazz;

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
	 * @see org.sylvani.bot.IHandler#handle(org.sylvani.bot.ISession, org.sylvani.bot.connector.ms.model.Activity)
	 */
	@Override
	public void handle(ISession session, IActivity activity) {
		if (resultBeanClazz != null && session.getAttribute(resultBeanName) == null) {
			try {
				Serializable resultBean = initBean();
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
			instanceState = new Integer(0);
			// finish?
			session.activeDialogFinished();
		}
		IDialog activeDialog = dialogs.get(instanceState.intValue());
		session.setActiveDialog(activeDialog);
		instanceState++;
		session.setAttribute(instanceStateKey, instanceState);

		activeDialog.handle(session, activity);
	}

	/**
	 * initializes a new result bean
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected Serializable initBean() throws InstantiationException, IllegalAccessException {
		return resultBeanClazz.newInstance();
	}
}
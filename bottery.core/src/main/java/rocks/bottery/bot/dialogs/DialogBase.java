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

import io.rincl.Rincled;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.connector.GenericActivity;

/**
 * Abstract base class that handles preparatino of an answer
 * 
 * @author Harald Kuhn
 *
 */
public abstract class DialogBase implements IDialog, Rincled {

	public abstract IModel<String> getText(IActivity request, ISession session);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IHandler#handle(org.sylvani.bot.ISession, org.sylvani.bot.IActivity)
	 */
	@Override
	public void handle(ISession session, IActivity request) {
		GenericActivity answer = session.getConnector().newReplyTo(request);
		fillActivity(request, answer, session);
		session.send(answer);
	}

	protected void fillActivity(IActivity request, GenericActivity response, ISession session) {
		response.setText(getText(request, session).getObject());
	}

}
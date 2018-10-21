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
package rocks.bottery.bot.universal;

import java.util.Locale;

import rocks.bottery.bot.ContextBase;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.connector.IConnector;

/**
 * Remembers the conversation.
 * 
 * @author Harald Kuhn
 */
public class UniversalSession extends ContextBase implements ISession {

	private IBot	   bot;
	private IDialog	   dialog;
	private IConnector connector;
	private String	   id;
	private Locale	   locale;

	protected UniversalSession(String id, IBot bot, IConnector connector, Locale locale) {
		this.bot = bot;
		this.connector = connector;
		this.id = id;
		this.locale = locale;
	}

	@Override
	public void send(IActivity activity) {
		if (bot.getBotConfig().getArchive() != null) {
			bot.getBotConfig().getArchive().handle(this, activity);
		}
		connector.send(activity);
	}

	@Override
	public IBot getBot() {
		return bot;
	}

	@Override
	public void setActiveDialog(IDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public IDialog getActiveDialog() {
		return dialog;
	}

	@Override
	public IConnector getConnector() {
		return connector;
	}

	@Override
	public void activeDialogFinished() {
		dialog = null;
	}

	@Override
	public void activeDialogFinished(IActivity activity) {
		activeDialogFinished();
		getBot().handle(this, activity);
	}

	@Override
	public void invalidate() {
		getBot().invalidate(this);
	}

	@Override
	public String getResolvedResource(String key) {
		String resource = getBot().getBotConfig().getLocalizer().getString(locale, key);
		if (getBot().getBotConfig().getResolver() != null) {
			return getBot().getBotConfig().getResolver().resolveVariables(resource, this);
		}
		return resource;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getChannel() {
		return connector.getChannel();
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}

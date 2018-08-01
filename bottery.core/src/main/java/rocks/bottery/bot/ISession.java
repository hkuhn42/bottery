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

import java.util.Locale;

import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.connector.IConnector;

/**
 * A bot session. A session is generally created for one chat or thread (depending on the implementation) with one or
 * more other entities (usually other bots or people for one connector
 * 
 * @author Harald Kuhn
 */
public interface ISession extends IContext {

	/**
	 * get this sessions id
	 * 
	 * @return
	 */
	String getId();

	/**
	 * send the given activity to this session (This is a convenience method to send on the connector)
	 * 
	 * @param activity
	 */
	public void send(IActivity activity);

	/**
	 * access the global bot context
	 * 
	 * @return
	 */
	public IBot getBot();

	/**
	 * @deprecated use connector registry to find connector for channel
	 * @return
	 */
	@Deprecated
	public IConnector getConnector();

	/**
	 * get the channel
	 * 
	 * @return the channel
	 */
	public String getChannel();

	/**
	 * set the active dialog
	 * 
	 * @param dialog
	 *            the new dialog
	 */
	public void setActiveDialog(IDialog dialog);

	/**
	 * Get the active dialog
	 * 
	 * @return the current active dialog
	 */
	public IDialog getActiveDialog();

	/**
	 * finish the active dialog and wait for a new activity
	 */
	public void activeDialogFinished();

	/**
	 * finish the active dialog and delegate directly to the next
	 */
	public void activeDialogFinished(IActivity activity);

	/**
	 * invalidate this session. the session is removed from the bot and cleaned up
	 */
	public void invalidate();

	/**
	 * gets a string resource from the bot and resolves it using the default resolver shortcut for
	 * 
	 * <pre>
	 * String resource = session.getBot().getResources().getString(key);
	 * String resolvedResource = session.getBot().getBotConfig().getVariableResolver().resolve(resource, session);
	 * </pre>
	 * 
	 * if no resolver is configured, the resource is returned without substitution
	 * 
	 * @param key
	 *            the key of the resource
	 * @return the resolved string
	 */
	public String getResolvedResource(String key);

	/**
	 * Get the locale of this session
	 * 
	 * @return the locale of this session
	 */
	public Locale getLocale();

	/**
	 * Set the locale of this session
	 * 
	 * @param locale
	 *            the new locale
	 */
	public void setLocale(Locale locale);
}

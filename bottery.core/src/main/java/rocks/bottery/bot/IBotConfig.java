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

import java.util.List;
import java.util.Locale;

import rocks.bottery.bot.i18n.ILocalizer;
import rocks.bottery.bot.interceptors.IInterceptor;
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.messaging.IMessagingConfig;

/**
 * Bot global config and context (shared between all bots)
 * 
 * @author Harald Kuhn
 */
public interface IBotConfig extends IMessagingConfig {

	/**
	 * get the active localizer
	 * 
	 * @return
	 */
	ILocalizer getLocalizer();

	/**
	 * get all recognizers
	 * 
	 * @return
	 */
	List<IRecognizer> getRecognizers();

	/**
	 * get the active variable resolver
	 * 
	 * @return
	 */
	IVariableResolver getResolver();

	/**
	 * get the session store
	 * 
	 * @return
	 */
	ISessionStore getSessionStore();

	/**
	 * get the activity archive
	 * 
	 * @return
	 */
	IActivityArchive getArchive();

	/**
	 * set the localizer
	 * 
	 * @param localizer
	 */
	void setLocalizer(ILocalizer localizer);

	/**
	 * set the recognizers
	 * 
	 * @param recognizer
	 */
	void setRecognizers(List<IRecognizer> recognizer);

	/**
	 * set the variable resolver
	 * 
	 * @param resolver
	 */
	void setResolver(IVariableResolver resolver);

	/**
	 * set the session store
	 * 
	 * @param sessionStore
	 */
	void setSessionStore(ISessionStore sessionStore);

	/**
	 * set the activity archive
	 * 
	 * @param archive
	 */
	void setArchive(IActivityArchive archive);
	// add dev config

	// add secruity config

	/**
	 * Get the List of in interceptors, to add an interceptor, add it to the list
	 * 
	 * @return the List of in interceptors
	 */
	List<IInterceptor> getInInterceptors();

	/**
	 * Get the List of out interceptors, to add an interceptor, add it to the list
	 * 
	 * @return the List of out interceptors
	 */
	List<IInterceptor> getOutInterceptors();

	Locale getDefaultLocale();

}
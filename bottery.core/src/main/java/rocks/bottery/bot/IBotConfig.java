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
package rocks.bottery.bot;

import java.util.List;

import rocks.bottery.bot.i18n.ILocalizer;
import rocks.bottery.bot.interceptors.IInterceptor;
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.messaging.IMessagingConfig;

/**
 * Bot global context (shared between all bots)
 * 
 * @author Harald Kuhn
 *
 */
public interface IBotConfig extends IMessagingConfig {

	ILocalizer getLocalizer();

	IRecognizer getRecognizer();

	IVariableResolver getResolver();

	ISessionStore getSessionStore();

	IActivityArchive getArchive();

	void setLocalizer(ILocalizer localizer);

	void setRecognizer(IRecognizer recognizer);

	void setResolver(IVariableResolver resolver);

	void setSessionStore(ISessionStore sessionStore);

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

}
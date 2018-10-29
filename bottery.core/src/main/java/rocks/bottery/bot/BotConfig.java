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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rocks.bottery.bot.i18n.ILocalizer;
import rocks.bottery.bot.interceptors.IInterceptor;
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.messaging.MessagingConfig;

/**
 * Default bot config. If a settings starts with "Crypt " (note the blanc) it is considered to be encrypted with the
 * active ICrypt implementation and will be decrypted by getSetting before beeing returned
 * 
 * @author Harald Kuhn
 */
public class BotConfig extends MessagingConfig implements IBotConfig {

	protected ILocalizer		 localizer;
	protected List<IRecognizer>	 recognizers;
	protected IVariableResolver	 resolver;
	protected ISessionStore		 sessionStore;

	protected IActivityArchive	 archive;

	protected List<IInterceptor> inInterceptors;
	protected List<IInterceptor> outInterceptors;

	protected Locale			 defaultLocale;

	public BotConfig() {
		super();
		inInterceptors = new ArrayList<>();
		outInterceptors = new ArrayList<>();
		recognizers = new ArrayList<>();
		defaultLocale = Locale.getDefault();
	}

	@Override
	public ILocalizer getLocalizer() {
		return localizer;
	}

	@Override
	public List<IRecognizer> getRecognizers() {
		return recognizers;
	}

	@Override
	public void setLocalizer(ILocalizer localizer) {
		this.localizer = localizer;
	}

	@Override
	public void setRecognizers(List<IRecognizer> recognizers) {
		this.recognizers = recognizers;

	}

	@Override
	public List<IInterceptor> getInInterceptors() {
		return inInterceptors;
	}

	@Override
	public List<IInterceptor> getOutInterceptors() {
		return outInterceptors;
	}

	@Override
	public IVariableResolver getResolver() {
		return resolver;
	}

	@Override
	public void setResolver(IVariableResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public ISessionStore getSessionStore() {
		return sessionStore;
	}

	@Override
	public void setSessionStore(ISessionStore sessionStore) {
		this.sessionStore = sessionStore;
	}

	@Override
	public IActivityArchive getArchive() {
		return archive;
	}

	@Override
	public void setArchive(IActivityArchive archive) {
		this.archive = archive;
	}

	public void setSetting(String setting, String value) {
		properties.setProperty(setting, value);
	}

	@Override
	public Locale getDefaultLocale() {
		return defaultLocale;
	}
}
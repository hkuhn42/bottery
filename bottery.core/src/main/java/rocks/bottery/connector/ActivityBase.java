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
package rocks.bottery.connector;

import java.io.Serializable;
import java.util.Locale;

import rocks.bottery.bot.ContextBase;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.recognizers.IIntent;

/**
 * Abstract base class for activities
 * 
 * @author Harald Kuhn
 */
public abstract class ActivityBase extends ContextBase implements IActivity, Serializable {

	private static final long serialVersionUID = 1L;

	protected IIntent		  intent;
	protected Locale		  locale;

	public ActivityBase() {
		super();
	}

	@Override
	public IIntent getIntent() {
		return intent;
	}

	@Override
	public void setIntent(IIntent intent) {
		this.intent = intent;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

}
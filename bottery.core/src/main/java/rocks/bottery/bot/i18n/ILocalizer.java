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
package rocks.bottery.bot.i18n;

import java.util.Date;
import java.util.Locale;

/**
 * Utility for i18n. This will be a future replacement for rincl
 * 
 * @author Harald Kuhn
 *
 */
public interface ILocalizer {

	public abstract String getString(String context, Locale locale, String key, Object... params);

	public abstract String getString(Object context, Locale locale, String key, Object... params);

	public abstract String getString(Locale locale, String key, Object... params);

	public abstract String format(Locale locale, Date date, String pattern);

	public abstract String formatDate(Locale locale, Date date);

	public abstract String formatTime(Locale locale, Date date);

	public abstract String formatDateTime(Locale locale, Date date);

}

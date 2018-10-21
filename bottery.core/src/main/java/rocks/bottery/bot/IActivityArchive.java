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

import rocks.bottery.bot.interceptors.IInterceptor;

/**
 * An archive for activities organized by conversations
 * 
 * Bots should check the botconfig or an archive, if one is present, it should be the first handler in the chain so that
 * the messages are preserved like they arrived at the bot
 * 
 *
 * @author Harald Kuhn
 */
public interface IActivityArchive extends IInterceptor {

	public void removeConversation(String id);

	public List<IActivity> getActivitiesByConversation(String id);

	public IActivity getActivityById(String id);

	public void archive(IActivity activity);

}

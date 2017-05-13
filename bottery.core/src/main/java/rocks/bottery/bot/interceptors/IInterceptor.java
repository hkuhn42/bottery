/**
 *    Copyright (C) 2016-2017 Harald Kuhn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.bot.interceptors;

import rocks.bottery.bot.IHandler;

/**
 * An interceptor / filter for incoming Activities
 * 
 * Interceptors must throw a NullPointerException if init is not called once before handle
 * 
 * @author hkuhn
 */
public interface IInterceptor extends IHandler {

	/**
	 * Adds the next Handler in the chain to this interceptor
	 * 
	 * @param nextInChain
	 */
	public void chain(IHandler nextInChain);

}

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

/**
 * Defines an api for encrypting and decrypting properties. This is used to encrypt / decrypt properties by bot config
 * It is recommended to use this to secure any credentials
 * 
 * @author Harald Kuhn
 *
 */
public interface ICrypt {

	/**
	 * Decrypt an encrypted value
	 * 
	 * @param encrypted
	 * @return
	 */
	public String decrypt(String encrypted);

	/**
	 * Encrypt an unencrypted value
	 * 
	 * @param plain
	 * @return
	 */
	public String encrypt(String plain);

}

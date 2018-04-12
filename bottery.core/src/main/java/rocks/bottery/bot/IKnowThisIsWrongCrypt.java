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
 * This crypt implementation does exactly nothing It should not be used except for diagnostic of crypto problems
 * 
 * @author Harald Kuhn
 *
 */
public class IKnowThisIsWrongCrypt implements ICrypt {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.util.ICrypt#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(String encrypted) {
		return encrypted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.util.ICrypt#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(String plain) {
		return plain;
	}

}

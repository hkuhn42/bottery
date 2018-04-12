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
/**
 * 
 */
package rocks.bottery.bot.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;

import javax.crypto.SecretKey;

/**
 * Utility for managing AES Keystores for the AESCrypt implementation
 * 
 * can be used to generate a new keystore by
 * 
 * java ... AESKeystoreHelper password
 * 
 * This will create a java JCEKS keystore with a AES 128 Bit Key named Bot.jks
 * 
 * @author Harald Kuhn
 */
public class AESKeystoreHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		AESKeystoreHelper e = new AESKeystoreHelper();
		SecretKey key = e.newKey();
		e.save(key, args[0]);
	}

	public SecretKey newKey() throws Exception {
		javax.crypto.KeyGenerator keyFactory = javax.crypto.KeyGenerator.getInstance("AES");
		keyFactory.init(128);

		return keyFactory.generateKey();
	}

	public void save(SecretKey spec, String passwd) throws Exception {
		KeyStore ks = KeyStore.getInstance("JCEKS");
		ks.load(null);

		ks.setKeyEntry(AESHelper.ALIAS, spec, passwd.toCharArray(), null);
		File keystoreFile = new File(AESHelper.KEYSTORE);
		FileOutputStream fStream = new FileOutputStream(keystoreFile);
		ks.store(fStream, passwd.toCharArray());
		fStream.close();

		System.out.println("new keystore written to " + keystoreFile.getAbsolutePath());
	}

}

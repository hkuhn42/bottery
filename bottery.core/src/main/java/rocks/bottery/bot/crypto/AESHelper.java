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
package rocks.bottery.bot.crypto;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility for AES encrypt and decrypt
 * 
 * keeps a transient copy of the key from the keystore for performance
 * 
 * The password for the keystore and entry are always the same. It is retrieved from the system property
 * KEYSTORE_PASSWORD
 * 
 * TO encrypt a value use this classes main method like this
 * 
 * java ... AESHelper secretText -DKEYSTORE_PASSWORD=password
 * 
 * @author hkuhn
 */
public class AESHelper {

	private static final String			   KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD";

	static final String					   ENCODING			 = "ISO-8859-1";

	static final String					   KEYSTORE			 = "Bot.jks";

	static final String					   ALIAS			 = "BotKey";

	private static transient SecretKeySpec key;

	/**
	 * Decrypt a string encrypted with this util
	 * 
	 * @param value
	 *            the encrypted value
	 * @return the decrypted value
	 * @throws Exception
	 *             if something went wrong :)
	 */
	static String decrypt(String value) throws Exception {
		byte[] input = Base64.getDecoder().decode(value);
		byte[] result = doChiper(ALIAS, KEYSTORE, input, Cipher.DECRYPT_MODE);
		return new String(result, ENCODING);
	}

	/**
	 * Encrypt a string
	 * 
	 * @param value
	 *            the string to encrypt
	 * @return the encrypted value
	 * @throws Exception
	 *             if something went wrong :)
	 */
	static String encrypt(String value) throws Exception {
		byte[] input = value.getBytes(ENCODING);
		byte[] result = doChiper(ALIAS, KEYSTORE, input, Cipher.ENCRYPT_MODE);
		return Base64.getEncoder().encodeToString(result);
	}

	static byte[] doChiper(String alias, String keystore, byte[] value, int mode) throws Exception {

		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec spec = loadKey(alias, keystore);
		cipher.init(mode, spec);

		return cipher.doFinal(value);
	}

	static SecretKeySpec loadKey(String alias, String keystore) throws Exception {
		if (key != null) {
			return key;
		}
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(keystore);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		String password = System.getProperty(KEYSTORE_PASSWORD);
		if (password == null || password.length() < 1) {
			throw new NullPointerException("password for keystore:" + keystore + " was not found");
		}
		KeyStore ks = KeyStore.getInstance("JCEKS");
		ks.load(is, password.toCharArray());

		is.close();

		key = (SecretKeySpec) ks.getKey(alias, password.toCharArray());
		return key;
	}

	public static void main(String[] args) throws Exception {
		String encrypted = AESHelper.encrypt(args[0]);
		System.out.println(encrypted);
		String reborn = AESHelper.decrypt(encrypted);
		System.out.println(reborn);
	}
}

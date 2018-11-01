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
package rocks.bottery.connector.line;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;

/**
 * Verifies the messages with a messageDigest
 * 
 * @author Harald Kuhn
 *
 */
public class MessageDigestInterceptor extends AbstractPhaseInterceptor<Message> {

	private String channelSecret = null; // Channel secret string;

	public MessageDigestInterceptor(String channelSecret) {
		super(Phase.RECEIVE);
		this.channelSecret = channelSecret;
	}

	@Override
	public void handleMessage(Message message) {
		InputStream is = message.getContent(InputStream.class);
		if (is != null) {
			@SuppressWarnings("unchecked")
			Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
			String xLineSignature = headers.get("X-Line-Signature").get(0);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				IOUtils.copy(is, bos);
				String httpRequestBody = new String(bos.toByteArray(), "UTF-8");
				SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
				Mac mac = Mac.getInstance("HmacSHA256");
				mac.init(key);
				byte[] source = httpRequestBody.getBytes("UTF-8");
				String signature = Base64.getEncoder().encodeToString(mac.doFinal(source));
				if (!xLineSignature.equals(signature)) {
					throw new SecurityException("Invalid X-Line-Signature");
				}

				// the content must be given back to ensure correct procssing
				message.setContent(InputStream.class, new ByteArrayInputStream(bos.toByteArray()));
			}
			catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
				Logger.getLogger(MessageDigestInterceptor.class).warn("could not generate signature", e);
				throw new RuntimeException(e);
			}

		}
	}

}
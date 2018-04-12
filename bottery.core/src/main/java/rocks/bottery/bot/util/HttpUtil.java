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
package rocks.bottery.bot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * @author Harald Kuhn
 *
 */
public class HttpUtil {

	public File download(String url) throws IOException {
		HttpClient httpClient = null;
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			long len = entity.getContentLength();
			InputStream inputStream = entity.getContent();
			// How do I write it?
			File tmpFile = File.createTempFile(String.valueOf(url.hashCode()), "tmp");
			try (FileOutputStream oStream = new FileOutputStream(tmpFile)) {
//				IOUtils.copy(inputStream, oStream);
			}
			return tmpFile;
		}
		return null;
	}

}

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
package rocks.bottery.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 * Factory for initiating JAX-RS Proxy API Client.
 * 
 * Supports Jackson 1 and Jackson 2 as serialization libs with jackson 1 as default
 * 
 * @author Harald Kuhn
 *
 */
public class ClientFactory<T> {

	private boolean useJackson2 = true;

	/**
	 * Initiate a new client factory of type T with jackson 1
	 */
	public ClientFactory() {
	}

	/**
	 * Initiate a new client factory of type T
	 * 
	 * @param useJackson2
	 *            wether to use jackson 2
	 */
	public ClientFactory(boolean useJackson2) {
		this.useJackson2 = useJackson2;
	}

	public T prepareClientProxy(String url, Class<T> clientClass) {
		List<Object> providers = new ArrayList<>();
		if (useJackson2) {
			JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
			providers.add(provider);
		}
		else {
			// jackson 1 for old implementations
			org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider provider = new org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider();
			providers.add(provider);
		}

		T proxy = JAXRSClientFactory.create(url, clientClass, providers);

		Bus bus = WebClient.getConfig(proxy).getBus();
		bus.getInInterceptors().add(new LoggingInInterceptor());
		bus.getOutInterceptors().add(new LoggingOutInterceptor());

		HTTPConduit conduit = (HTTPConduit) WebClient.getConfig(proxy).getConduit();
		TLSClientParameters params = new TLSClientParameters();
		conduit.setTlsClientParameters(params);

		return proxy;
	}
}

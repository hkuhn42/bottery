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
package rocks.bottery.connector.ms.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.message.Message;

import rocks.bottery.connector.ms.model.OpenIdConfig;

/**
 * Proxy api for microsofts openID service for the botFramework
 * 
 * @author Harald Kuhn
 *
 */
public interface OpenIdAPI {
	@Path("/.well-known/openidconfiguration")
	@Produces("application/json")
	@GET
	public OpenIdConfig getOpenIdConfiguration();

	@Path("/.well-known/openidconfiguration")
	@Produces("application/json")
	@GET
	public Message getOpenIdConfigurationRaw();

}

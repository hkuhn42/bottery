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
package rocks.bottery.connector.stride.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS Interface definition for the atlassian auth API
 * 
 * @author Harald Kuhn
 */
public interface IAuthAPI {

	/**
	 * Request a token. In essence does something like:
	 * 
	 * curl --request POST \ --url 'https://api.atlassian.com/oauth/token' \ --header 'content-type: application/json' \
	 * --data '{"grant_type":"client_credentials","client_id": "{clientId}", "client_secret": "{clientSecret}"}'
	 * 
	 * The response contains the token and its expiration (in seconds), as well as the list of granted scopes (which
	 * will match the scopes previously configured in the app):
	 * 
	 * { "access_token": "xxx", "expires_in": 3600, "scope": "participate:conversation", "token_type": "Bearer" }
	 * 
	 * @param request
	 *            the token request
	 * @return the token
	 */
	@Path("/oauth/token")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Token getToken(TokenRequest request);

}
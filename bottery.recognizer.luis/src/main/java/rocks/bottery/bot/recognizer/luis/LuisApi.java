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
package rocks.bottery.bot.recognizer.luis;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * JAX-RS Proxy API definition for LUIS API
 * 
 * @author Harald Kuhn
 *
 */
@Path("/")
public interface LuisApi {

	@Path("/apps/{appId}")
	@GET
	@Consumes({ "application/x-www-form-urlencoded" })
	@Produces({ "application/json" })
	public LuisResponse query(@PathParam("appId") String appId, @QueryParam("subscription-key") String subscriptionKey, @QueryParam("q") String query);

}

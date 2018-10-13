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
package rocks.bottery.bot.recognizer.witai;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * See <a href="https://wit.ai/docs/http/20170307#get--message-link">get--message-link</a>
 * 
 * Interface for https://api.wit.ai/message?v=20170307&amp;q=how%20many%20people%20between%20Tuesday%20and%20Friday' \
 * -H 'Authorization: Bearer $TOKEN'
 * 
 * @author Harald Kuhn
 *
 */
@Path("/")
public interface WitAiApi {

	@Path("/message")
	@GET
	@Consumes({ "application/x-www-form-urlencoded" })
	@Produces({ "application/json" })
	public Response message(@HeaderParam("Authorization") String authorization, @QueryParam("v") String version, @QueryParam("q") String query);

}

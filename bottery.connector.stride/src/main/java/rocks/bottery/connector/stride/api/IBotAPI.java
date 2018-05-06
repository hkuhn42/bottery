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
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS definition of the client side api of a stride bot-These methods must be published on a local resource in order
 * to install the service and receive messages
 * 
 * @author Harald Kuhn
 */
@Path("/bot")
public interface IBotAPI {

	/**
	 * Delivers the JSON Descriptor of the bot service
	 * 
	 * @return a json string containing the stride service descriptor
	 */
	@GET
	@Path("/descriptor")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDescriptor();

	/**
	 * Called by stride cloud when this bot is installed somewhere
	 * 
	 * @param authorization
	 *            an auth header
	 * @param data
	 *            the data in json format
	 */
	@POST
	@Path("/installed")
	@Consumes(MediaType.APPLICATION_JSON)
	public void installed(@HeaderParam("Authorization") String authorization, String data);

	/**
	 * Called by stride cloud when this bot is uninstalled somewhere
	 * 
	 * @param authorization
	 *            an auth header
	 * @param data
	 *            the data in json format
	 */
	@POST
	@Path("/uninstalled")
	@Consumes(MediaType.APPLICATION_JSON)
	public void uninstalled(@HeaderParam("Authorization") String authorization, String data);

	/**
	 * Called when the bot is mentioned in a room in the stride cloud (must be installed first)
	 * 
	 * @param authorization
	 *            an auth header
	 * @param mention
	 *            the message object
	 */
	@POST
	@Path("/mention")
	@Produces(MediaType.APPLICATION_JSON)
	public void mention(@HeaderParam("Authorization") String authorization, Activity mention);

	/**
	 * Called when the bot is send a direct message from the stride cloud (must be installed first)
	 * 
	 * @param authorization
	 *            an auth header
	 * @param mention
	 *            the message object
	 */
	@POST
	@Path("/directMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public void directMessage(@HeaderParam("Authorization") String authorization, Activity data);

}

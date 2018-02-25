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
package rocks.bottery.connector.gitter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * 
 * https://api.gitter.im/v1
 * 
 * Authorization: Bearer {{token}}
 * 
 * @author Harald Kuhn
 *
 */
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface GitterAPI {

	@Path("/user/{userId}")
	@GET
	public User getUser(@HeaderParam("Authorization") String token, @PathParam("userId") String user);

	/**
	 * /v1/rooms
	 */
	@Path("/rooms")
	@GET
	public List<Room> listRooms(@HeaderParam("Authorization") String token, @PathParam("q") String query);

	/**
	 * /v1/rooms/:roomId/chatMessages?limit=50
	 */
	@Path("/rooms/{roomId}/chatMessages")
	@GET
	public List<Message> fetchMessages(@HeaderParam("Authorization") String token, @PathParam("roomId") String roomId, @QueryParam("limit") int limit,
	        @QueryParam("beforeId") String beforeId, @QueryParam("afterId") String afterId, @QueryParam("aroundId") String aroundId,
	        @QueryParam("q") String query);

	/**
	 * /v1/rooms/:roomId/chatMessages?limit=50
	 */
	@Path("/rooms/{roomId}/chatMessages")
	@POST
	public Message sendMessages(@HeaderParam("Authorization") String token, @PathParam("roomId") String roomId, @FormParam("text") String text);

	@Path("/user/{userId}/rooms/{roomId}/unreadItems")
	@POST
	public void markAsRead(@HeaderParam("Authorization") String token, @PathParam("userId") String userId, @PathParam("roomId") String roomId,
	        MessageList messages);
}

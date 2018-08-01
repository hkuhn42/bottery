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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * API for receiving messages from MS cloud
 * 
 * @author Harald Kuhn
 */
public interface IStrideAPI {

	/**
	 * Send a message to a room
	 * 
	 * In essence does:
	 * 
	 * curl -X POST \ https://api.atlassian.com \ -H 'authorization: Bearer {accessToken}' \ -H 'cache-control:
	 * no-cache' \ -H 'content-type: application/json' \ -d ' {"body": {"version": 1,"type": "doc","content": [{"type":
	 * "paragraph","content": [{"type": "text","text": "Hi everyone!"}]}]}}'
	 * 
	 * {"body": {"version":1, "type": "doc",
	 * "content":[{"type":"paragraph","content":[{"type":"text","content":[],"text":"Hello World, back!"}]}]}}
	 */
	@POST
	@Path("/site/{cloudId}/conversation/{conversationId}/message")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessageToRoom(@HeaderParam("authorization") String auth, @PathParam("cloudId") String cloudId,
	        @PathParam("conversationId") String conversationId, Message message);

	/**
	 * Send a message to a user
	 * 
	 * In essence does:
	 * 
	 * curl -X POST \ https://api.atlassian.com/site/{cloudId}/conversation/user/{userId}/message \ -H 'authorization:
	 * Bearer {accessToken}' \ -H 'content-type: application/json' \ -d '{"body": {"version": 1,"type": "doc","content":
	 * [{"type": "paragraph","content": [{"type": "text","text": "Hello you!"}]}]}}'
	 * 
	 */
	@POST
	@Path("/site/{cloudId}/conversation/{conversationId}/user/{userId}/message")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessageToUser(@HeaderParam("authorization") String auth, @PathParam("cloudId") String cloudId,
	        @PathParam("conversationId") String conversationId, @PathParam("userId") String userId, Message message);
}

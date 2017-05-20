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
package rocks.bottery.bot.connector.ms.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Harald Kuhn
 *
 */
@Path("/")
public interface AttachmentsApi {
	@GET
	@Path("/v3/attachments/{attachmentId}/views/{viewId}")

	@Produces({ "application/json", "text/json", "application/xml", "text/xml" })
	public Response attachmentsGetAttachment(@PathParam("attachmentId") String attachmentId,
			@PathParam("viewId") String viewId);

	@GET
	@Path("/v3/attachments/{attachmentId}")

	@Produces({ "application/json", "text/json", "application/xml", "text/xml" })
	public Response attachmentsGetAttachmentInfo(@PathParam("attachmentId") String attachmentId);
}

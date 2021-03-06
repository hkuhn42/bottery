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
package rocks.bottery.bot.recognizer.luis.qna;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * API for the Microsoft QnA Maker
 * 
 * Also see
 * <a href="https://docs.microsoft.com/en-us/azure/cognitive-services/QnAMaker/quickstarts/java#GetAnswers">QnAMaker
 * Quickstarts</a>
 * 
 * @author Harald Kuhn
 */
public interface QnaMakerAPI {

	/**
	 * Generate an answer from the knowlege base
	 * 
	 * @param authorization
	 *            the auth key
	 * @param kbId
	 *            the knowledbe base
	 * @param question
	 *            the question
	 * @return the answer
	 */
	@POST
	@Path("/knowledgebases/{kbId}/generateAnswer")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public QnaResponse generateAnswer(@HeaderParam("Authorization") String authorization, @PathParam("kbId") String kbId, QnaRequest question);

}
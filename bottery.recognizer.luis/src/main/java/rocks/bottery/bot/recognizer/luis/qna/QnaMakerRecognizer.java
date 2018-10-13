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

import java.io.IOException;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.recognizers.CommandIntent;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.bot.recognizers.RecognizerBase;
import rocks.bottery.util.ClientFactory;

/**
 * Recognizer which uses the Microsoft QnA Maker API to recognize intents
 * 
 * @author Harald Kuhn
 */
public class QnaMakerRecognizer extends RecognizerBase implements IRecognizer {

	private QnaMakerAPI	instance;

	private String		kbId;
	private String		auth;

	@Override
	public void init(IBotConfig config) throws IOException {

		auth = config.getSetting("luis.qna.auth");
		kbId = config.getSetting("luis.qna.kbId");
		instance = new ClientFactory<QnaMakerAPI>().prepareClientProxy(config.getSetting("luis.qna.host"), QnaMakerAPI.class);
	}

	@Override
	public IIntent recognize(ISession session, IActivity activity) {
		QnaRequest question = new QnaRequest();
		question.setQuestion(activity.getText());
		question.setTop(1);
		QnaResponse response = instance.generateAnswer(auth, kbId, question);
		if (response.getAnswers() != null && response.getAnswers().size() > 0) {
			return new CommandIntent("qna", response.getAnswers().get(0).getAnswer());
		}
		else {
			return null;
		}
	}
}
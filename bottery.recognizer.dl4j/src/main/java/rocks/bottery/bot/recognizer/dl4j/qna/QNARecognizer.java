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
package rocks.bottery.bot.recognizer.dl4j.qna;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.recognizer.dl4j.DL4JRecognizerBase;
import rocks.bottery.bot.recognizers.CommandIntent;
import rocks.bottery.bot.recognizers.IIntent;

/**
 * A Deeplearning4J based recognizer for questions and answers
 * 
 * @author Harald Kuhn
 */
public class QNARecognizer extends DL4JRecognizerBase {

	public static final String DL4J_FILE_ANSWERS = "dl4j.file.answers";
	private List<String>	   answers;
	private String			   answersFileName;

	@Override
	public IIntent recognize(ISession session, IActivity activity) {
		try {
			// vectorize text (question)
			INDArray features = vectorizer.transform(activity.getText());
			// find answer by processing vector in network
			INDArray output = neuralNetwork.output(features, false);
			int answerIndex = Nd4j.argMax(output, 1).getInt(0);
			// get the text answer
			String answer = answers.get(answerIndex);

			return new CommandIntent("answer", answer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void init(IBotConfig config) throws IOException {
		if (answersFileName == null) {
			answersFileName = config.getSetting(DL4J_FILE_ANSWERS);
		}
		answers = Files.readAllLines(new File(answersFileName).toPath());

		super.init(config);
	}
}

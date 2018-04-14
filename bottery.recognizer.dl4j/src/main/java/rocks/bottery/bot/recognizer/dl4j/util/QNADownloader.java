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
package rocks.bottery.bot.recognizer.dl4j.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Utility to download a list of questions and answers from a website (args[0] = url, args[1] css class of questions,
 * args[2] cass class of answers)
 * 
 * @author Harald Kuhn
 */
public class QNADownloader {

	public void fetch(String url, String questClass, String answerClass) throws IOException {
		File questions = new File("./data/questions.txt");
		questions.createNewFile();

		File answers = new File("./data/answers.txt");
		answers.createNewFile();

		byte[] lineSeperator = new String("\n").getBytes("UTF-8");

		try (FileOutputStream questionsStream = new FileOutputStream(questions); FileOutputStream answersStream = new FileOutputStream(answers)) {
			Document doc = Jsoup.connect(url).get();
			log(doc.title());
			Elements newsHeadlines = doc.select(questClass);

			Elements newsTexts = doc.select(answerClass);

			if (newsHeadlines.size() == newsTexts.size()) {
				for (int i = 0; i < newsHeadlines.size(); i++) {

					String text = newsHeadlines.get(i).text().replace('\n', ' ').replace('\r', ' ');
					questionsStream.write(text.getBytes("UTF-8"));
					questionsStream.write(lineSeperator);

					String text2 = newsTexts.get(i).text().replace('\n', ' ').replace('\r', ' ');
					log(text2);
					answersStream.write(text2.getBytes("UTF-8"));
					answersStream.write(lineSeperator);
				}
				questionsStream.flush();
				answersStream.flush();
			}

		}
		finally {
		}
		System.out.println("saved to " + questions.getPath());
	}

	private void log(String text) {
		System.out.println(text);
	}

	public static void main(String[] args) throws IOException {
		QNADownloader x = new QNADownloader();
		x.fetch(args[0], args[1], args[2]);
	}
}

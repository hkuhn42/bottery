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
package rocks.bottery.connector.lex;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import com.amazonaws.services.lexruntime.model.PostTextRequest;
import com.amazonaws.services.lexruntime.model.PostTextResult;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.bot.recognizers.RecognizerBase;

/**
 * Recognizer which uses the Amazons Lex API to recognize intents
 * 
 * @author Harald Kuhn
 *
 */
public class LexRecognizer extends RecognizerBase {

	private AmazonLexRuntime			 client;

	private String						 botName;
	private String						 botAlias;

	private AWSStaticCredentialsProvider credentialsProvider;

	@Override
	public void init(IBotConfig config) throws IOException {
		super.init(config);
		client = AmazonLexRuntimeClientBuilder.standard().withRegion(Regions.fromName(config.getSetting("lex.aws.region"))).build();
		botAlias = config.getSetting("lex.bot.alias");

		botName = config.getSetting("lex.bot.name");

		credentialsProvider = new AWSStaticCredentialsProvider(new AWSCredentials() {

			@Override
			public String getAWSSecretKey() {
				return config.getSetting("lex.aws.secretKey");
			}

			@Override
			public String getAWSAccessKeyId() {
				return config.getSetting("lex.aws.accessKeyId");
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IRecognizer#recognize(rocks.bottery.bot.ISession, rocks.bottery.bot.IActivity)
	 */
	@Override
	public IIntent recognize(ISession session, IActivity activity) {

		PostTextRequest textRequest = new PostTextRequest();

		textRequest.setRequestCredentialsProvider(credentialsProvider);

		textRequest.setBotName(botName);
		textRequest.setBotAlias(botAlias);
		textRequest.setUserId(activity.getFrom().getId());
		textRequest.setInputText(activity.getText());
		PostTextResult textResult = client.postText(textRequest);

		return new LexIntent(textResult);
	}
}
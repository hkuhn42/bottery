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
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.bagofwords.vectorizer.BagOfWordsVectorizer;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import rocks.bottery.bot.recognizer.dl4j.qna.QNARecognizer;

/**
 * Trainer for a qna model (creates the model and data for the {@link QNARecognizer}
 * 
 * This is heavily influenced by https://github.com/wmeints/qna-bot.git and his presentation. Unforunalely he did not
 * specify a license so i wrote the the whole thing from scratch based on his example.
 * 
 * This is more an example than a ready to use tool. Please customize as needed.
 */
public class QNATrainer {

	public static void main(String[] args) {
		QNATrainer trainer = new QNATrainer();
		trainer.train();
	}

	public void train() {

		StatsStorage statsStorage = new InMemoryStatsStorage();

		UIServer server = UIServer.getInstance();

		server.attach(statsStorage);

		train(statsStorage);
		server.stop();
	}

	protected static void train(StatsStorage statsStorage) {
		try {
			// questions
			File trainingDataFile = new File("data/questions.txt");

			TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
			tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

			BagOfWordsVectorizer vectorizer = new BagOfWordsVectorizer.Builder().setIterator(new FileSentenceIterator(trainingDataFile, "UTF-8"))
			        .setTokenizerFactory(tokenizerFactory).build();

			vectorizer.fit();

			List<String> lines = Files.readAllLines(trainingDataFile.toPath(), Charset.forName("UTF-8"));
			INDArray features = Nd4j.zeros(lines.size(), vectorizer.getVocabCache().numWords());

			for (int index = 0; index < lines.size(); index++) {
				String line = lines.get(index);
				INDArray lineVector = vectorizer.transform(line);

				features.putRow(index, lineVector);
			}

			// answers

			List<String> answers = Files.readAllLines(new File("data/answers.txt").toPath());
			INDArray labels = Nd4j.zeros(answers.size(), answers.size());

			for (int index = 0; index < answers.size(); index++) {
				INDArray indArray = Nd4j.zeros(answers.size());
				indArray.putScalar(index, 1.0);
				labels.putRow(index, indArray);
			}

			int iterations = 500;
			double learningRate = 0.05;

			// basic setup
			ListBuilder listBuilder = new NeuralNetConfiguration.Builder().seed(42).iterations(iterations).learningRate(learningRate)
			        .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).list();

			// the layers
			DenseLayer reluLayer = new DenseLayer.Builder().nIn(vectorizer.getVocabCache().numWords()).nOut(1024).activation(Activation.RELU).build();
			OutputLayer softmaxLayer = new OutputLayer.Builder(LossFunctions.LossFunction.MCXENT).nIn(1024).nOut(labels.columns())
			        .activation(Activation.SOFTMAX).build();

			listBuilder.layer(0, reluLayer).layer(1, softmaxLayer);
			// final setup
			listBuilder.backprop(true).pretrain(false);
			// create the config
			MultiLayerConfiguration configuration = listBuilder.build();
			// and create a network from the config
			MultiLayerNetwork network = new MultiLayerNetwork(configuration);

			// add ui to monitor
			network.setListeners(new StatsListener(statsStorage));
			network.init();

			network.fit(features, labels);

			System.out.println("x " + labels.columns());
			// Evaluation the network
			Evaluation evaluation = new Evaluation(vectorizer.getVocabCache().numWords());
			evaluation.eval(labels, features, network);

			// save
			ModelSerializer.writeModel(network, new File("model/classifier.bin"), true);

			WordVectorSerializer.writeVocabCache(vectorizer.getVocabCache(), new File("model/vocabulary.bin"));
		}
		catch (IOException ex) {
			System.out.println("Failed to read training data" + ex);
			ex.printStackTrace();
		}
	}
}

package rocks.bottery.bot.recognizer.dl4j;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.bagofwords.vectorizer.BagOfWordsVectorizer;
import org.deeplearning4j.bagofwords.vectorizer.BagOfWordsVectorizer.Builder;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.util.ModelSerializer;

import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.recognizers.RecognizerBase;

public abstract class DL4JRecognizerBase extends RecognizerBase {

	public static final String	   DL4J_FILE_VOCABULARY	= "dl4j.file.vocabulary";
	public static final String	   DL4J_FILE_CLASSIFIER	= "dl4j.file.classifier";
	protected MultiLayerNetwork	   neuralNetwork;
	protected BagOfWordsVectorizer vectorizer;

	public DL4JRecognizerBase() {
		super();
	}

	public void init(IBotConfig config) throws IOException {
		String classifierFileName = null;
		String vocabularyFileName = null;
		if (classifierFileName == null) {
			classifierFileName = config.getSetting(DL4J_FILE_CLASSIFIER);
		}
		if (vocabularyFileName == null) {
			vocabularyFileName = config.getSetting(DL4J_FILE_VOCABULARY);
		}

		File classifierFile = new File(classifierFileName);

		neuralNetwork = ModelSerializer.restoreMultiLayerNetwork(classifierFile);

		TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
		tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

		BagOfWordsVectorizer.Builder builder = new BagOfWordsVectorizer.Builder();
		builder.setVocab(WordVectorSerializer.readVocabCache(new File(vocabularyFileName)));
		Builder setTokenizerFactory = builder.setTokenizerFactory(tokenizerFactory);
		vectorizer = setTokenizerFactory.build();
	}

}
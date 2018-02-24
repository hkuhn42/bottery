package rocks.bottery.examples.hello.dialogflow;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.connector.console.ConsoleConnector;;

/**
 * This example uses the console connector for a hello world
 */
public class HelloDialogflowRecognizer {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		WeatherBot handler = new WeatherBot();
		new ConsoleConnector().register(handler);
	}
}

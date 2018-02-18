package rocks.bottery.examples.hello.luis;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.connector.console.ConsoleConnector;;

/**
 * This example uses the msConnector for a hello world
 */
public class HelloLuisRecognizer {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		AlarmBot handler = new AlarmBot();
		new ConsoleConnector().register(handler);
	}
}

package rocks.bottery.examples.console;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.bot.connector.console.ConsoleConnector;
import rocks.bottery.examples.HelloBot;;

/**
 * This example uses the msConnector for a hello world
 */
public class HelloConsoleConnector {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new ConsoleConnector().listen(new HelloBot());
	}
}

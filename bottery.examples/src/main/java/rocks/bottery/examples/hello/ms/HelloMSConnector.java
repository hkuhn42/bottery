package rocks.bottery.examples.hello.ms;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.bot.connector.ms.MSConnector;
import rocks.bottery.examples.HelloBot;;

/**
 * This example uses the msConnector for a hello world
 */
public class HelloMSConnector {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new MSConnector().listen(new HelloBot());
	}
}

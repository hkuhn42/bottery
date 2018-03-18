package rocks.bottery.examples.hello.telegram;

import org.apache.log4j.BasicConfigurator;

import rocks.bottery.connector.TelegramConnector;
import rocks.bottery.examples.EchoBot;;

/**
 * This example uses the msConnector for a hello world
 */
public class HelloTelegram {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new TelegramConnector("telegram").register(new EchoBot());
	}
}

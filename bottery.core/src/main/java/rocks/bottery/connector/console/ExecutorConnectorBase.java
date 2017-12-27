package rocks.bottery.connector.console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 
 * @author Harald Kuhn
 *
 */
public abstract class ExecutorConnectorBase extends ConnectorBase {

	private Logger			  logger = Logger.getLogger(ExecutorConnectorBase.class);

	protected ExecutorService executor;

	public ExecutorConnectorBase() {
		super();
		executor = Executors.newCachedThreadPool();
	}

	@Override
	public void shutdown() {
		try {
			logger.debug("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			logger.debug("tasks interrupted");
		}
		finally {
			if (!executor.isTerminated()) {
				logger.debug("cancel non-finished tasks");
			}
			executor.shutdownNow();
			logger.debug("shutdown finished");
		}
	}

}
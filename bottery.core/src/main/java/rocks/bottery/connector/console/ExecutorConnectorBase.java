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
package rocks.bottery.connector.console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * Connector base class which offers an executor service for polling
 * 
 * @author Harald Kuhn
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
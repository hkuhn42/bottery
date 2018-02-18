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
package rocks.bottery.notifications;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * An ExecutorService based trigger which triggers the notifiers after a given Callable returns. Can e.g. be used to
 * offload sending messages after a long running job completes
 * 
 * @author Harald Kuhn
 *
 */
public class ExecutorTrigger<T> extends TriggerBase {

	private Logger			  logger = Logger.getLogger(ExecutorTrigger.class);

	private Callable<T>		  callable;

	private T				  result;

	protected ExecutorService executor;

	public ExecutorTrigger(Callable<T> callable) {
		this.callable = callable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.notifications.TriggerBase#startInternal()
	 */
	@Override
	protected void startInternal() {
		executor = Executors.newCachedThreadPool();

		Future<T> future = executor.submit(callable);

		try {
			result = future.get();

			for (INotifier iNotifier : notifiers) {
				iNotifier.notify(this, context);
			}

			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			logger.debug("tasks interrupted");
		}
		catch (ExecutionException e) {
			logger.debug("task execution failed", e);
		}
		finally {
			if (!executor.isTerminated()) {
				logger.debug("cancel non-finished tasks");
			}
			executor.shutdownNow();
			logger.debug("shutdown finished");
		}
	}

	public T getResult() {
		return result;
	}

}

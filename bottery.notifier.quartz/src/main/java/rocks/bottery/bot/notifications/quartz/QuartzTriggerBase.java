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
package rocks.bottery.bot.notifications.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import rocks.bottery.notifications.INotifier;
import rocks.bottery.notifications.TriggerBase;

/**
 * A base class to build quartz based triggers
 * 
 * It defines a factory method for the schedulerBuilder to configure the schedule. Uses the default scheduler.
 * 
 * @author Harald Kuhn
 *
 */
public abstract class QuartzTriggerBase extends TriggerBase {

	@Override
	protected void startInternal() {
		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = buildScheduler();

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(BotteryNotifierJob.class).withIdentity("botteryNotifierJob", "botteryGroup").build();

			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("botteryNotifierTrigger", "botteryGroup").startNow().withSchedule(setupScheduleBuilder())
			        .build();

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);

			// and start it off
			scheduler.start();

			scheduler.shutdown();

		}
		catch (SchedulerException se) {
			// FIXME: handle
			se.printStackTrace();
		}
	}

	protected Scheduler buildScheduler() throws SchedulerException {
		return StdSchedulerFactory.getDefaultScheduler();
	}

	protected abstract SimpleScheduleBuilder setupScheduleBuilder();

	protected class BotteryNotifierJob implements Job {

		@Override
		public void execute(JobExecutionContext jobContext) throws JobExecutionException {
			for (INotifier iNotifier : notifiers) {
				iNotifier.notify(QuartzTriggerBase.this, context);
			}
		}

	};

}

package com.dunkware.common.util.executor;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.concurrency.ReusableCountDownLatch;

public class DExecutor {

	private Logger logger = LoggerFactory.getLogger(getClass());	
	private Marker marker = MarkerFactory.getMarker("Executor");
	private final Queue<Task> awaitingTasks;
	private final ScheduledExecutorService executor;
	private final int corePoolSize;

	private long delayTimeout = 30;
	private TimeUnit delayTimeUnit = TimeUnit.SECONDS;

	private AtomicLong completedCount = new AtomicLong(0);
	private AtomicLong timeoutCount = new AtomicLong(0);
	private ReusableCountDownLatch phaser = new ReusableCountDownLatch();
	private volatile int poolSize;

	// private CountUpAndDownLatch latch = new CountUpAndDownLatch(0);

	/**
	 * Creates a new {@code TimeoutTaskThreadPoolExecutor} with the given core pool
	 * size. The pool should be greater or equals than 2 because one thread is
	 * reserved to schedule cancellation task.
	 *
	 * @param corePoolSize the number of threads to keep in the pool, even if they
	 *                     are idle, unless {@code allowCoreThreadTimeOut} is set
	 * @throws IllegalArgumentException if {@code corePoolSize < 0}
	 */
	public DExecutor(int corePoolSize) {
		this.awaitingTasks = new LinkedBlockingQueue<>();
		this.executor = Executors.newScheduledThreadPool(corePoolSize);
		this.corePoolSize = corePoolSize;
	
	//	this.poolSize = Runtime.getRuntime().availableProcessors();

	}
	
	
	
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

	public DExecutor(int corePoolSize, long delayTimeout, TimeUnit delayTimeUnit) {
		this.awaitingTasks = new LinkedBlockingQueue<>();
		this.executor = Executors.newScheduledThreadPool(corePoolSize);
		this.corePoolSize = corePoolSize;
		this.poolSize = 0;
		this.delayTimeUnit = delayTimeUnit;
		this.delayTimeout = delayTimeout;

	}
	
	public DExecutorStats getStats() { 
		DExecutorStats s = new DExecutorStats();
		s.setCompletedCount(getCompletedTaskCount());
		s.setPendingCount(getPendingTaskCount());
		s.setPoolSize(poolSize);
		s.setTimeoutCount(getTimeoutTaskCount());
		return s;
	}
	
	public int getPendingTaskCount() { 
		return phaser.getCount();
	}

	public void awaitWhileTasksRunning() throws InterruptedException {
		phaser.waitTillZero();
	}

	public long getCompletedTaskCount() {
		return completedCount.get();
	}

	public long getTimeoutTaskCount() {
		return timeoutCount.get();
	}

	public void execute(Runnable task) {
		execute(task, this.delayTimeout, this.delayTimeUnit);
	}

	public void execute(Runnable task, long delayTimeout, TimeUnit unit) {
		phaser.increment();
		awaitingTasks.offer(new Task(task, delayTimeout, unit));
		executeWaitingTask();
	}

	public synchronized void shutdown() {
		executor.shutdown();
		awaitingTasks.clear();
	}

	public boolean isTerminated() {
		return executor.isTerminated();
	}
	
	public int getActiveTaskCount() { 
		return phaser.getCount();
	}
	
	public boolean hasActiveTasks() { 
		if(getActiveTaskCount() > 0) { 
			return true;
		}
		return false;
	}

	private synchronized void executeWaitingTask() {
		if (executor.isShutdown()) {
			return;
		}

		//if ((corePoolSize - poolSize) > 0) {
			final Task nextTask = awaitingTasks.poll();
			if (nextTask != null) {
				poolSize++;
				final Future<?> taskHandler = executor.submit(new Runnable() {
					@Override
					public void run() {
						try {
							try {
								nextTask.task.run();	
							} catch (Exception e) {
								logger.error("Task Class {} Threw Exception {}",nextTask.task.getClass().getName(),e.toString(),e);
							}
							
							phaser.decrement();
							completedCount.incrementAndGet();
						} finally {
							poolSize--;
							executeWaitingTask();
						}
					}
				});
				
				
				// okay so then we have a Future<?> taskHandler
				// we schedule a runnable for delay and if its still running
				// we delay interrupt or cancel the task. 
				
				executor.schedule(new Runnable() {
					@Override
					public void run() {
						
						if (!taskHandler.isDone()) {
							taskHandler.cancel(true);
							logger.error(marker, "Executor task time out " + nextTask.task.getClass().getName() );
							completedCount.decrementAndGet();
							timeoutCount.incrementAndGet();
							phaser.decrement();
							poolSize--;
						}

					}
				}, nextTask.delayTimeout, nextTask.unit);
			}
		//}
	}

	private static class Task {
		Runnable task;
		long delayTimeout;
		TimeUnit unit;
	
		
		public Task(Runnable task, long delayTimeout, TimeUnit unit ) {
			this.task = task;
			this.delayTimeout = delayTimeout;
			this.unit = unit;
		}
	}

}

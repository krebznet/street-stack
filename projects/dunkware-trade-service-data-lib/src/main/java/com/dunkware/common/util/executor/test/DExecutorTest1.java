package com.dunkware.common.util.executor.test;

import java.util.concurrent.TimeUnit;

import com.dunkware.common.util.executor.DExecutor;

public class DExecutorTest1 {

	
	public static void main(String[] args) {
		DExecutorTest1 test1 = new DExecutorTest1();
	}
	
	
	private DExecutor executor;
	
	public DExecutorTest1() { 
		executor = new DExecutor(5, 30, TimeUnit.SECONDS);
		
		RunWhileExecutorEmpty runner = new RunWhileExecutorEmpty();
		runner.start();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			
		}
		
		RunnableTimmer timmer = new RunnableTimmer("Test5sec", 5);
		executor.execute(timmer);
		
		try {
			Thread.sleep(5000000);
		} catch (Exception e) {
			
		}
	}
	
	
	
	private class RunWhileExecutorEmpty extends Thread { 
		
		public void run() { 
			while(true) { 
				try {
					executor.awaitWhileTasksRunning();
					System.out.println("Running While Executor is Empty");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	private class RunnableTimmer implements Runnable { 
		
		private int runTime;
		private String name;
		
		public RunnableTimmer(String name, int runTime) { 
			this.name = name; 
			this.runTime = runTime;
		}
		
		public void run() { 
			System.out.println("Runnable " + name + " Starting");
			try {
				Thread.sleep(runTime * 1000);
			} catch (Exception e) {
				System.out.println("Runnable Exception " + name);
			} finally { 
				System.out.println("Runnable " + name   + " Finished");
			}
		}
	}
}

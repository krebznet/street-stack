package com.dunkware.logger.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DunkLogger {
	
	private static DunkLogger instance = null;
	
	private List<DunkLogSubscription> subscriptions = new ArrayList<DunkLogSubscription>();
	private LogHandler logHandler; 
	private BlockingQueue<DunkLog> logQueue = new LinkedBlockingQueue<DunkLog>();
	
	private DunkLogger() { 
		// initialize here 
	}
	
	public static DunkLogger get() { 
		if(instance == null) { 
			instance = new DunkLogger();
		}
		return instance;
		
	}
	
	public static DunkLogSubscriptionBuilder subscription() { 
		return null;
	}
	
	public static DunkLogFactoryBuilder factory() { 
		return null;
	}
	
	public void log(DunkLog log) { 
		logQueue.add(log);
	}
	

	private class LogHandler extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				// determine if there is subscriber for the log 
				
			}
		}
	}
	
	
}

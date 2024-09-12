package com.dunkware.trade.tick.provider.atick.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveTickStatsLogger {
	
	private static ActiveTickStatsLogger instance; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AtomicLong request = new AtomicLong();
	private AtomicLong response = new AtomicLong();
	private AtomicLong quoteCount = new AtomicLong();
	private AtomicLong responseProblem = new AtomicLong();
	
	private LoggerThread loggerThread = new LoggerThread();
	
	
	public static ActiveTickStatsLogger get() { 
		if(instance == null) { 
			instance = new ActiveTickStatsLogger();
			
		}
		return instance; 
	}
	
	private ActiveTickStatsLogger() { 
		loggerThread = new LoggerThread();
		loggerThread.start();
	}
	
	public void incrementQuoteCount() { 
		quoteCount.incrementAndGet();
	}
	
	public void incrementRequest() { 
		request.incrementAndGet();
	}
	
	public void incrementResponse() { 
		response.incrementAndGet();
	}
	
	public void incrementResponseError() { 
		responseProblem.incrementAndGet();
	}
	
	private class LoggerThread extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
					long diff = request.get() - response.get();
					if(diff > 15) { 
						logger.warn("Snapshot Response Problem Diff {} ",diff);
					} else { 
						if(logger.isDebugEnabled()) { 
							logger.debug("Snapshot Request {} Response {} Response Problem {} Quote Count{}",request.get(),response.get(),responseProblem.get(),quoteCount.get());
						}
					}
				} catch (Exception e) {
					logger.error("WTF Exception " + e.toString());
				}
			}
		}
	}

}

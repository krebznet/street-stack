package com.dunkware.trade.net.service.streamstats.server.controller;

import java.util.Vector;

public class StreamStatsRunnablePool {

	private Vector<StreamStatsRunnable> pool = new Vector<StreamStatsRunnable>();
	
	public static StreamStatsRunnablePool create(int poolSize) { 
		return new StreamStatsRunnablePool(poolSize);
	}
	
	private StreamStatsRunnablePool(int poolSize) {
		int i = 0;
		while(i < poolSize) { 
			pool.add(new StreamStatsRunnable());
			i++;
		}
	}
	
	public StreamStatsRunnable acquire() { 
		if(pool.size() == 0) { 
			return new StreamStatsRunnable();
		} else { 
			return pool.remove(pool.size() - 1);
		}
	}
	
	
	public void release(StreamStatsRunnable runnable) { 
		pool.add(runnable);
	}
}

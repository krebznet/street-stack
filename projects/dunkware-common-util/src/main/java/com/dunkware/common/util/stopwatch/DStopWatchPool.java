package com.dunkware.common.util.stopwatch;

import java.util.Vector;

public class DStopWatchPool {

	
	public static DStopWatchPool createPool(int poolSize) { 
		return new DStopWatchPool(poolSize);
	}
	
	private Vector<DStopWatch> watchList = new Vector<DStopWatch>();

	
	public void dispose() { 
		watchList.removeAllElements();
	}
	
	public DStopWatchPool(int poolSize) {

		int i = 0;
		while (i < poolSize) {
			watchList.add(DStopWatch.create());
			i++;
		}
	}

	public DStopWatch acquire() {
		if (watchList.size() == 0) {
			return DStopWatch.create();
		}
		return watchList.remove(watchList.size() - 1);

	}

	public void release(DStopWatch watch) {
		watchList.add(watch);
	}
}

package com.dunkware.utils.core.stopwatch;

public class StopWatch {

	public static StopWatch newInstance() { 
		return new StopWatch();
	}
	
	private volatile long _startTime = 0;
	private volatile long _stopTime  = 0;
	private boolean running = false; 
	
	public void start() {
		_startTime = System.nanoTime();
		running = true; 
	}
	
	public void stop() {
		_stopTime = System.nanoTime();
		running = false;
	}
	
	public double seconds() { 
		long dureation = _stopTime - _startTime;
		double seconds = ((double) dureation) / 1E9;
		return seconds;
	}

	public boolean isRunning() { 
		return running;
	}
}

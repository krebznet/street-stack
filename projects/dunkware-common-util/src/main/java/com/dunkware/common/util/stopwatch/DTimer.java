package com.dunkware.common.util.stopwatch;

import java.time.Duration;
import java.time.LocalTime;

public class DTimer {

	private LocalTime startTime;

	public static DTimer newInstance() { 
		return new DTimer();
	}
	public void start() {
		this.startTime = LocalTime.now();
	}

	public void stop() {
		startTime = null;
		
	}

	public long elapsedSeconds() {
		if(startTime == null) { 
			return 0;
		}
		LocalTime now = LocalTime.now();
		Duration duration = Duration.between(startTime, now);
		return duration.getSeconds();
	}

}

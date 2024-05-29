package com.dunkware.utils.core.time;

import java.time.LocalDateTime;

public class DunkTimeDuration {
	
	public static DunkTimeDuration build(LocalDateTime start, LocalDateTime stop) { 
		return new DunkTimeDuration(start,stop);
	}
	
	
	private LocalDateTime start; 
	private LocalDateTime stop;
	
	private DunkTimeDuration(LocalDateTime start, LocalDateTime stop) { 
		this.start = start;
		this.stop = stop;
	}
	
	
	
	public LocalDateTime getStartTime() { 
		return start;
	}
	
	public LocalDateTime getStopTime() { 
		return stop;
	}
	
	public long getStartTimestamp() { 
		return DunkTime.toMilliseconds(start);
	}
	
	public long getStopTimestamp() { 
		return DunkTime.toMilliseconds(stop);
	}

}

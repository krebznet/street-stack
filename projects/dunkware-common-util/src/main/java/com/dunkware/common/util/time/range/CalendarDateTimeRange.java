package com.dunkware.common.util.time.range;

import java.time.LocalDateTime;

public class CalendarDateTimeRange implements CalendarRange {

	private LocalDateTime start;
	private LocalDateTime stop;
	
	public static CalendarDateTimeRange newInstance(LocalDateTime start, LocalDateTime stop) { 
		return new CalendarDateTimeRange(start, stop);
	}
	
	private CalendarDateTimeRange(LocalDateTime start, LocalDateTime stop) {
		this.start = start;
		this.stop = stop;
	}
	
	public LocalDateTime getStart() { 
		return start;
		
	}
	
	public LocalDateTime getStop() { 
		return stop;
	}
	
	public boolean inRange(LocalDateTime dt) { 
		if(dt.isAfter(start) && stop.isAfter(dt)) { 
			return true; 
		}
		return false;
	}
}

package com.dunkware.common.util.time.range;

import java.time.LocalTime;

public class LocalTimeRange implements CalendarRange {
	
		
	public static LocalTimeRange newInstance(LocalTime start, LocalTime stop) { 
		return new LocalTimeRange(start,stop);
	}
	
	LocalTime start;
	LocalTime stop; 

	
	private LocalTimeRange(LocalTime start, LocalTime stop) { 
		this.start = start;
		this.stop = stop; 
	}
	
	
	public boolean inRange(LocalTime time) { 
		if(time.isAfter(start) && stop.isAfter(time)) { 
			return true;
		}
		return false;
	}

}

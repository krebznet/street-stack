package com.dunkware.common.util.time.range;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CalendarDateRange implements CalendarRange {
	

	private LocalDate start;
	private LocalDate stop;
	
	public static CalendarDateRange newInstance(LocalDate start, LocalDate stop) { 
		return new CalendarDateRange(start, stop);
	}
	
	private CalendarDateRange(LocalDate start, LocalDate stop) {
		this.start = start.minusDays(1);
		this.stop = stop.plusDays(1);
	}
	
	public LocalDate getStart() { 
		return start;
		
	}
	
	public LocalDate getStop() { 
		return stop;
	}
	
	public boolean inRange(LocalDate dt) { 
		if(dt.isAfter(start) && dt.isBefore(stop)) { 
			return true; 
		}
		return false;
	}

}

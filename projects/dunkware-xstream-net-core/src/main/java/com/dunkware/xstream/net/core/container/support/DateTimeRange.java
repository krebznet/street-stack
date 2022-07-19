package com.dunkware.xstream.net.core.container.support;

import java.time.LocalDateTime;

public class DateTimeRange {
	
	private LocalDateTime start; 
	private LocalDateTime stop;
	
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getStop() {
		return stop;
	}
	public void setStop(LocalDateTime stop) {
		this.stop = stop;
	} 
	
	

}

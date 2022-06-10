package com.dunkware.xstream.net.core.container.search2.filter;

import java.time.LocalDateTime;

public class EntityFilterTimeRange {
	
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

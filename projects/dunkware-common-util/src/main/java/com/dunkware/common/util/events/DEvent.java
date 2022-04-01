package com.dunkware.common.util.events;

import com.dunkware.common.util.dtime.DZonedDateTime;

public abstract class DEvent {

	private DZonedDateTime timestamp; 
	
	public DEvent() { 
		this.timestamp = DZonedDateTime.now();
	}

	public DZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	
	
	
	
	
	
	
	
	
}

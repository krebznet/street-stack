package com.dunkware.utils.core.events;

import java.time.LocalDateTime;

public abstract class DunkEvent {

	private LocalDateTime timestamp; 
	
	public DunkEvent() { 
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	
	
	
	
	
	
	
	
	
}

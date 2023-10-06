package com.dunkware.trade.service.data.model.search;

import java.time.LocalDateTime;

public class EntitySignalCount {
	
	private int signal; 
	private int entity; 
	private int count;
	private LocalDateTime lastDateTime;
	
	public int getSignal() {
		return signal;
	}
	public void setSignal(int signal) {
		this.signal = signal;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public LocalDateTime getLastDateTime() {
		return lastDateTime;
	}
	public void setLastDateTime(LocalDateTime lastDateTime) {
		this.lastDateTime = lastDateTime;
	} 
	
	
	
	
	
	
	
	
	
	

}

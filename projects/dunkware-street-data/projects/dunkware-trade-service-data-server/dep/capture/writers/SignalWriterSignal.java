package com.dunkware.trade.net.data.server.stream.capture.writers;

import java.time.LocalDateTime;

public class SignalWriterSignal {

	private int count; 
	private int id; 
	private int lastEntity; 
	private LocalDateTime lastTime;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLastEntity() {
		return lastEntity;
	}
	public void setLastEntity(int lastEntity) {
		this.lastEntity = lastEntity;
	}
	public LocalDateTime getLastTime() {
		return lastTime;
	}
	public void setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
	} 
	
	
	
	
}

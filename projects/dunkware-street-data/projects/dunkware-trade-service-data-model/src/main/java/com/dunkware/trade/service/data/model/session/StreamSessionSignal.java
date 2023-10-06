package com.dunkware.trade.service.data.model.session;

import java.time.LocalDateTime;

public class StreamSessionSignal {

	private int id; 
	private int count; 
	private LocalDateTime lastTime; 
	private int lastIdentifty;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public LocalDateTime getLastTime() {
		return lastTime;
	}
	public void setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
	}
	public int getLastIdentifty() {
		return lastIdentifty;
	}
	public void setLastIdentifty(int lastIdentifty) {
		this.lastIdentifty = lastIdentifty;
	} 
	
	
	
	
}

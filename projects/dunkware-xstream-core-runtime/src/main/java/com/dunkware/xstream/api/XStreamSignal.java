package com.dunkware.xstream.api;

import java.time.LocalTime;

public class XStreamSignal {

	private LocalTime time; 
	private int signalId; 
	private int entityId; 
	
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	
	
}

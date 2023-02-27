package com.dunkware.trade.service.beach.server.runtime.core;

import java.time.LocalTime;

public class BeachStreamSignal {
	
	private String signalIdent; 
	private int signalId; 
	private String entityIdent; 
	private String entityId; 
	private LocalTime timestamp;
	
	public String getSignalIdent() {
		return signalIdent;
	}
	public void setSignalIdent(String signalIdent) {
		this.signalIdent = signalIdent;
	}
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public String getEntityIdent() {
		return entityIdent;
	}
	public void setEntityIdent(String entityIdent) {
		this.entityIdent = entityIdent;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public LocalTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	

}

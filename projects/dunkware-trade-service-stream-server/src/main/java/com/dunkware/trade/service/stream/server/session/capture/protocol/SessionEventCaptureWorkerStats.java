package com.dunkware.trade.service.stream.server.session.capture.protocol;

public class SessionEventCaptureWorkerStats {
	
	private long pendingEvents; 
	private int entityEvents; 
	private int entitySnapshots;
	
	
	public long getPendingEvents() {
		return pendingEvents;
	}
	public void setPendingEvents(long pendingEvents) {
		this.pendingEvents = pendingEvents;
	}
	public int getEntityEvents() {
		return entityEvents;
	}
	public void setEntityEvents(int entityEvents) {
		this.entityEvents = entityEvents;
	}
	public int getEntitySnapshots() {
		return entitySnapshots;
	}
	public void setEntitySnapshots(int entitySnapshots) {
		this.entitySnapshots = entitySnapshots;
	}
	
	

}

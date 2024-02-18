package com.dunkware.trade.service.stream.worker.session.container.core;

public class WorkerContainerStats {

	private int entityCount; 
	private int snapshotCount; 
	private int signalCount; 
	private int timeCount; 
	private String time; 
	private String workerId;
	private String lastSnapshot; 
	
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(int snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public int getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getLastSnapshot() {
		return lastSnapshot;
	}
	public void setLastSnapshot(String lastSnapshot) {
		this.lastSnapshot = lastSnapshot;
	} 
	
	
	
	
}

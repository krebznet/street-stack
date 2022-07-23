package com.dunkware.xstream.container;

public class ContainerStats {
	
	private int snapshotCount; 
	private int signalCount; 
	private int entityCount; 
	private String lastSnapshot; 
	private String lastSignal; 
	private String lastTimeUpdate;
	
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
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public String getLastSnapshot() {
		return lastSnapshot;
	}
	public void setLastSnapshot(String lastSnapshot) {
		this.lastSnapshot = lastSnapshot;
	}
	public String getLastSignal() {
		return lastSignal;
	}
	public void setLastSignal(String lastSignal) {
		this.lastSignal = lastSignal;
	}
	public String getLastTimeUpdate() {
		return lastTimeUpdate;
	}
	public void setLastTimeUpdate(String lastTimeUpdate) {
		this.lastTimeUpdate = lastTimeUpdate;
	} 
	
	

}

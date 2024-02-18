package com.dunkware.xstream.model.snapshot.tick.stats;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnapshotTickMakerSignalStats {
	
	private int signalId; 
	private int signalCount; 
	private LocalTime lastSignalTime; 
	private Map<Integer,Integer> entityCounts = new ConcurrentHashMap<Integer,Integer>();
	
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public LocalTime getLastSignalTime() {
		return lastSignalTime;
	}
	public void setLastSignalTime(LocalTime lastSignalTime) {
		this.lastSignalTime = lastSignalTime;
	}
	public Map<Integer, Integer> getEntityCounts() {
		return entityCounts;
	}
	public void setEntityCounts(Map<Integer, Integer> entityCounts) {
		this.entityCounts = entityCounts;
	}
	
	
	
	

}

package com.dunkware.xstream.model.snapshot.tick.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnapshotTickMakerStats {
	
	private Map<Integer,SnapshotTickMakerEntityStats> entityStats = new ConcurrentHashMap<Integer,SnapshotTickMakerEntityStats>();
	private Map<Integer,SnapshotTickMakerSignalStats> signalStats = new ConcurrentHashMap<Integer, SnapshotTickMakerSignalStats>();
	
	public Map<Integer, SnapshotTickMakerEntityStats> getEntityStats() {
		return entityStats;
	}
	public void setEntityStats(Map<Integer, SnapshotTickMakerEntityStats> entityStats) {
		this.entityStats = entityStats;
	}
	public Map<Integer, SnapshotTickMakerSignalStats> getSignalStats() {
		return signalStats;
	}
	public void setSignalStats(Map<Integer, SnapshotTickMakerSignalStats> signalStats) {
		this.signalStats = signalStats;
	}
	
	
	
	

}

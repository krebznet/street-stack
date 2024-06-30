package com.dunkware.xstream.model.snapshot.tick.stats;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnapshotTickMakerEntityStats {
	
	private LocalTime firstTime; 
	private LocalTime lastTime; 
	private long seconds;
	private int skippedSeconds;
	private int entityId; 
	private Map<Integer,Integer> varSnapshotsCount = new ConcurrentHashMap<Integer,Integer>();
	private Map<Integer,LocalTime> varSnapshotsFirstTime = new ConcurrentHashMap<Integer, LocalTime>();
	private Map<Integer,LocalTime> varSnapshotsLastTime = new ConcurrentHashMap<Integer, LocalTime>();
	private Map<Integer,Number> varSnapshotsLastValue = new ConcurrentHashMap<Integer, Number>();
	
	public LocalTime getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(LocalTime firstTime) {
		this.firstTime = firstTime;
	}
	public LocalTime getLastTime() {
		return lastTime;
	}
	public void setLastTime(LocalTime lastTime) {
		this.lastTime = lastTime;
	}
	public long getSeconds() {
		return seconds;
	}
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}
	public int getSkippedSeconds() {
		return skippedSeconds;
	}
	public void setSkippedSeconds(int skippedSeconds) {
		this.skippedSeconds = skippedSeconds;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public Map<Integer, Integer> getVarSnapshotsCount() {
		return varSnapshotsCount;
	}
	public void setVarSnapshotsCount(Map<Integer, Integer> varSnapshotsCount) {
		this.varSnapshotsCount = varSnapshotsCount;
	}
	public Map<Integer, LocalTime> getVarSnapshotsFirstTime() {
		return varSnapshotsFirstTime;
	}
	public void setVarSnapshotsFirstTime(Map<Integer, LocalTime> varSnapshotsFirstTime) {
		this.varSnapshotsFirstTime = varSnapshotsFirstTime;
	}
	public Map<Integer, LocalTime> getVarSnapshotsLastTime() {
		return varSnapshotsLastTime;
	}
	public void setVarSnapshotsLastTime(Map<Integer, LocalTime> varSnapshotsLastTime) {
		this.varSnapshotsLastTime = varSnapshotsLastTime;
	}
	public Map<Integer, Number> getVarSnapshotsLastValue() {
		return varSnapshotsLastValue;
	}
	public void setVarSnapshotsLastValue(Map<Integer, Number> varSnapshotsLastValue) {
		this.varSnapshotsLastValue = varSnapshotsLastValue;
	}
	
	
	

	
	
	
	
	


}

package com.dunkware.xstream.model.processor;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XStreamProcessorSignalType {
	
	private int streamId; 
	private int signalId; 
	private int signalCount; 
	private LocalTime lastSignalTime;  
	private LocalTime firstSignalTime;
	private Map<Integer,Integer> entitySignalCounts= new ConcurrentHashMap<Integer,Integer>();
	
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
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
	public LocalTime getFirstSignalTime() {
		return firstSignalTime;
	}
	public void setFirstSignalTime(LocalTime firstSignalTime) {
		this.firstSignalTime = firstSignalTime;
	}
	public Map<Integer, Integer> getEntitySignalCounts() {
		return entitySignalCounts;
	}
	public void setEntitySignalCounts(Map<Integer, Integer> entitySignalCounts) {
		this.entitySignalCounts = entitySignalCounts;
	} 
	
	
	
	

}

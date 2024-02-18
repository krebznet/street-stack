package com.dunkware.xstream.model.processor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XStreamProcessorStats {
	
	
	private int streamId; 
	private LocalDateTime startTime; 
	private LocalDateTime endTime; 
	
	private Map<Integer,XStreamProcessorEntity> entities = new ConcurrentHashMap<Integer,XStreamProcessorEntity>();
	private Map<Integer,XStreamProcessorSignalType> signals = new ConcurrentHashMap<Integer,XStreamProcessorSignalType>();
	
	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Map<Integer, XStreamProcessorEntity> getEntities() {
		return entities;
	}

	public void setEntities(Map<Integer, XStreamProcessorEntity> entities) {
		this.entities = entities;
	}

	public Map<Integer, XStreamProcessorSignalType> getSignals() {
		return signals;
	}

	public void setSignals(Map<Integer, XStreamProcessorSignalType> signals) {
		this.signals = signals;
	}
	
	
	
	

}

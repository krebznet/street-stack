package com.dunkware.xstream.model.processor;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XStreamProcessorEntity {
	
	private int streamId; 
	private long entityId; 
	private LocalTime startTime; 
	
	//private Map<Integer,XStreamSessionEntityVar> vars = new ConcurrentHashMap<Integer,XStreamSessionEntityVar>();

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}


	
	

}

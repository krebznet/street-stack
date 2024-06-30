package com.dunkware.xstream.model.snapshot;

public class SnapshotSignal {

	private int streamId; 
	private int signalId; 
	private long timestamp; 
	private int entity;
	
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
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	} 
	
	
}

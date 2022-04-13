package com.dunkware.xstream.data.publishers;

public class StreamEventPublisherStats {
	
	private String identifier; 
	private String brokers; 
	private String topic; 
	
	private long queueSize; 
	private long eventCount; 
	private long snapshotCount;
	private long signalCount; 
	private long sessionStopCount;
	private long sessionStartCount;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getBrokers() {
		return brokers;
	}
	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public long getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(long queueSize) {
		this.queueSize = queueSize;
	}
	public long getEventCount() {
		return eventCount;
	}
	public void setEventCount(long eventCount) {
		this.eventCount = eventCount;
	}
	public long getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public long getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(long signalCount) {
		this.signalCount = signalCount;
	}
	public long getSessionStopCount() {
		return sessionStopCount;
	}
	public void setSessionStopCount(long sessionStopCount) {
		this.sessionStopCount = sessionStopCount;
	}
	public long getSessionStartCount() {
		return sessionStartCount;
	}
	public void setSessionStartCount(long sessionStartCount) {
		this.sessionStartCount = sessionStartCount;
	} 
	
	

}

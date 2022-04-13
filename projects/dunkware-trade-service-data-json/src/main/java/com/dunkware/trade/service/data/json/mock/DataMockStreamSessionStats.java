package com.dunkware.trade.service.data.json.mock;

import com.dunkware.trade.service.data.json.enums.DataStreamStatus;

public class DataMockStreamSessionStats {
	
	private String streamIdentifier; 
	private String sessionId; 
	private int publishedSignals; 
	private int queuedSignals; 
	private int publishedSnapshots; 
	private int queuedSnapshots;
	
	private DataStreamStatus getState; 
	
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getPublishedSignals() {
		return publishedSignals;
	}
	public void setPublishedSignals(int publishedSignals) {
		this.publishedSignals = publishedSignals;
	}
	public int getQueuedSignals() {
		return queuedSignals;
	}
	public void setQueuedSignals(int queuedSignals) {
		this.queuedSignals = queuedSignals;
	}
	public int getPublishedSnapshots() {
		return publishedSnapshots;
	}
	public void setPublishedSnapshots(int publishedSnapshots) {
		this.publishedSnapshots = publishedSnapshots;
	}
	public int getQueuedSnapshots() {
		return queuedSnapshots;
	}
	public void setQueuedSnapshots(int queuedSnapshots) {
		this.queuedSnapshots = queuedSnapshots;
	}
	public DataStreamStatus getGetState() {
		return getState;
	}
	public void setGetState(DataStreamStatus getState) {
		this.getState = getState;
	}
	
	
	
	

}

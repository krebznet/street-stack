package com.dunkware.spring.messaging;

import java.beans.Transient;

import com.dunkware.common.util.dtime.DDateTime;

public class DunkNetBean {

	private long messageCount; 
	private int errorCount; 
	private DDateTime startTime; 
	private String id; 
	private int nodeCount;
	
	
	public long getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(long messageCount) {
		this.messageCount = messageCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public DDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DDateTime startTime) {
		this.startTime = startTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNodeCount() {
		return nodeCount;
	}
	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	} 
	
	@Transient
	public void incrementMessageCount()	 {
		this.messageCount++;
	}
	
	
	
}

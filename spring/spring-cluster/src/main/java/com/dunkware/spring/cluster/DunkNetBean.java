package com.dunkware.spring.cluster;

import java.beans.Transient;
import java.time.LocalDateTime;

public class DunkNetBean {

	private long messageCount; 
	private int errorCount; 
	private LocalDateTime startTime; 
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
	
	
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
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

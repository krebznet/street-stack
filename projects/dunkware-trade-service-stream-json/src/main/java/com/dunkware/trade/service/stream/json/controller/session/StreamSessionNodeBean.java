package com.dunkware.trade.service.stream.json.controller.session;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamSessionNodeBean extends ObservableBean {

	private String status; 
	private String workerId; 
	private String nodeId; 
	private long tasksCompleted; 
	private long tasksPending;
	private long tasksExpired; 
	private int signalCount; 
	private int issueCount; 
	private String streamTime; 
	private String systemTime; 
	private int entityCount;
	private int id; 
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public long getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(long tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}
	public long getTasksPending() {
		return tasksPending;
	}
	public void setTasksPending(long tasksPending) {
		this.tasksPending = tasksPending;
	}
	public long getTasksExpired() {
		return tasksExpired;
	}
	public void setTasksExpired(long tasksExpired) {
		this.tasksExpired = tasksExpired;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	public String getStreamTime() {
		return streamTime;
	}
	public void setStreamTime(String streamTime) {
		this.streamTime = streamTime;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	
	
	
	
	
	
}

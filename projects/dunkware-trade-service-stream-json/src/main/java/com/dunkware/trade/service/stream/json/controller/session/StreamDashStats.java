package com.dunkware.trade.service.stream.json.controller.session;

public class StreamDashStats {

	private String status; 
	private int tasksCompleted; 
	private long tasksPending; 
	private long tasksExpired;
	private int nodes;
	private long tickCount; 
	private long entityCount;
	private int signalCount;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(int tasksCompleted) {
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
	public int getNodes() {
		return nodes;
	}
	public void setNodes(int nodes) {
		this.nodes = nodes;
	}
	public long getTickCount() {
		return tickCount;
	}
	public void setTickCount(long tickCount) {
		this.tickCount = tickCount;
	}
	public long getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(long entityCount) {
		this.entityCount = entityCount;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	} 
	
	
	
	
}

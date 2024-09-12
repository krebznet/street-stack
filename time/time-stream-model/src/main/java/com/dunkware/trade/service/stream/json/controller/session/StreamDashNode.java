package com.dunkware.trade.service.stream.json.controller.session;

public class StreamDashNode {
	
	private String node; 
	private long tasksPending; 
	private long tasksCompleted; 
	private long tasksExpired; 
	private long tickCount; 
	private int entityCount; 
	private String streamTime; 
	private String systemTime;
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public long getTasksPending() {
		return tasksPending;
	}
	public void setTasksPending(long tasksPending) {
		this.tasksPending = tasksPending;
	}
	public long getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(long tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}
	public long getTasksExpired() {
		return tasksExpired;
	}
	public void setTasksExpired(long tasksExpired) {
		this.tasksExpired = tasksExpired;
	}
	public long getTickCount() {
		return tickCount;
	}
	public void setTickCount(long tickCount) {
		this.tickCount = tickCount;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
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
	
	
	

}

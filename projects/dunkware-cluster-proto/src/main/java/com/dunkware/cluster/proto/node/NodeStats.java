package com.dunkware.cluster.proto.node;

public class NodeStats {
	
	private String id; 
	
	private long messagesInboundPS;
	private long messagesInbound; 
	private long messagesOutboundPS;
	private long messagesOutbound; 
	private long taskPending; 
	private long tasksCompleted;
	private long tasksCompletedPS; 
	private long tasksFailed; 
	
	public NodeStats() { 
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getMessagesInboundPS() {
		return messagesInboundPS;
	}

	public void setMessagesInboundPS(long messagesInboundPS) {
		this.messagesInboundPS = messagesInboundPS;
	}

	public long getMessagesInbound() {
		return messagesInbound;
	}

	public void setMessagesInbound(long messagesInbound) {
		this.messagesInbound = messagesInbound;
	}

	public long getMessagesOutboundPS() {
		return messagesOutboundPS;
	}

	public void setMessagesOutboundPS(long messagesOutboundPS) {
		this.messagesOutboundPS = messagesOutboundPS;
	}

	public long getMessagesOutbound() {
		return messagesOutbound;
	}

	public void setMessagesOutbound(long messagesOutbound) {
		this.messagesOutbound = messagesOutbound;
	}

	public long getTaskPending() {
		return taskPending;
	}

	public void setTaskPending(long taskPending) {
		this.taskPending = taskPending;
	}

	public long getTasksCompleted() {
		return tasksCompleted;
	}

	public void setTasksCompleted(long tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}

	public long getTasksCompletedPS() {
		return tasksCompletedPS;
	}

	public void setTasksCompletedPS(long tasksCompletedPS) {
		this.tasksCompletedPS = tasksCompletedPS;
	}

	public long getTasksFailed() {
		return tasksFailed;
	}

	public void setTasksFailed(long tasksFailed) {
		this.tasksFailed = tasksFailed;
	}
	
	
	
	
	

}

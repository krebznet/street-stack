package com.dunkware.trade.service.stream.json.controller.session;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamSessionNodeBean extends ObservableBean {

	
	private String status; 
	private String workerId; 
	private String nodeId; 
	private int tickCount;
	private long tasksCompleted; 
	private long tasksPending;
	private long tasksExpired; 
	private int signalCount; 
	private int issueCount; 
	private String streamTime; 
	private String systemTime; 
	private int entityCount;
	private int id; 
	private String stopState; 
	private String stopError; 
	private long varSnapshotCount = 0; 
	private long varSnapshotQueue = 0; 
	private double varSnapshotSecondTime = 0.0;
	private long varSnapshotSecondCount = 0;
	private long varSnapshotFirstCaptureTime = 0;
	private long varSnapshotLastCaptureTime = 0;
	
	private List<String> stopProblems = new ArrayList<String>();
	
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
	public String getStopState() {
		return stopState;
	}
	public void setStopState(String stopState) {
		this.stopState = stopState;
	}
	public String getStopError() {
		return stopError;
	}
	public void setStopError(String stopError) {
		this.stopError = stopError;
	}
	public List<String> getStopProblems() {
		return stopProblems;
	}
	public void setStopProblems(List<String> stopProblems) {
		this.stopProblems = stopProblems;
	}
	public int getTickCount() {
		return tickCount;
	}
	public void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}
	public long getVarSnapshotCount() {
		return varSnapshotCount;
	}
	public void setVarSnapshotCount(long varSnapshotCount) {
		this.varSnapshotCount = varSnapshotCount;
	}
	public long getVarSnapshotQueue() {
		return varSnapshotQueue;
	}
	public void setVarSnapshotQueue(long varSnapshotQueue) {
		this.varSnapshotQueue = varSnapshotQueue;
	}
	public double getVarSnapshotSecondTime() {
		return varSnapshotSecondTime;
	}
	public void setVarSnapshotSecondTime(double varSnapshotSecondTime) {
		this.varSnapshotSecondTime = varSnapshotSecondTime;
	}
	public long getVarSnapshotSecondCount() {
		return varSnapshotSecondCount;
	}
	public void setVarSnapshotSecondCount(long varSnapshotSecondCount) {
		this.varSnapshotSecondCount = varSnapshotSecondCount;
	}
	public long getVarSnapshotFirstCaptureTime() {
		return varSnapshotFirstCaptureTime;
	}
	public void setVarSnapshotFirstCaptureTime(long varSnapshotFirstCaptureTime) {
		this.varSnapshotFirstCaptureTime = varSnapshotFirstCaptureTime;
	}
	public long getVarSnapshotLastCaptureTime() {
		return varSnapshotLastCaptureTime;
	}
	public void setVarSnapshotLastCaptureTime(long varSnapshotLastCaptureTime) {
		this.varSnapshotLastCaptureTime = varSnapshotLastCaptureTime;
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

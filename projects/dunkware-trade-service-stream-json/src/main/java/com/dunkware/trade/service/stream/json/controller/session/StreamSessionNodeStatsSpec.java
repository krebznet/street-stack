package com.dunkware.trade.service.stream.json.controller.session;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.trade.service.stream.json.cluster.spec.ClusterNodeStatsSpec;

public class StreamSessionNodeStatsSpec {

	private String nodeId; 
	private List<String> problems = new ArrayList<String>();
	private List<String> warnings = new ArrayList<String>();
	private int rowCount;
	private int tickerCount;
	private int signalCount; 
	private DTime startingTime; 
	private DTime startTime; 
	private StreamSessionNodeStatus status; 
	private long pendingTaskCount; 
	private long completedTaskCount; 
	private long timeoutTaskCount; 
	private DTime streamTime; 
	private DTime systemTime;
	private DTime lastDataTickTime; 
	private String workerId; 
	private String exception;
	private StreamSessionNodeState state; 
	private ClusterNodeStatsSpec node;
	
	public StreamSessionNodeStatsSpec() { 
		
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public long getTimeoutTaskCount() {
		return timeoutTaskCount;
	}

	public void setTimeoutTaskCount(long timeoutTaskCount) {
		this.timeoutTaskCount = timeoutTaskCount;
	}

	public StreamSessionNodeStatus getStatus() {
		return status;
	}

	public void setStatus(StreamSessionNodeStatus status) {
		this.status = status;
	}

	

	public long getPendingTaskCount() {
		return pendingTaskCount;
	}

	public void setPendingTaskCount(long pendingTaskCount) {
		this.pendingTaskCount = pendingTaskCount;
	}

	public long getCompletedTaskCount() {
		return completedTaskCount;
	}

	public void setCompletedTaskCount(long completedTaskCount) {
		this.completedTaskCount = completedTaskCount;
	}

	public DTime getStreamTime() {
		return streamTime;
	}

	public void setStreamTime(DTime streamTime) {
		this.streamTime = streamTime;
	}

	public DTime getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(DTime systemTime) {
		this.systemTime = systemTime;
	}

	public List<String> getProblems() {
		return problems;
	}

	public void setProblems(List<String> problems) {
		this.problems = problems;
	}

	public int getSignalCount() {
		return signalCount;
	}

	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}

	public DTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(DTime startingTime) {
		this.startingTime = startingTime;
	}

	public DTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}

	public ClusterNodeStatsSpec getNode() {
		return node;
	}

	public void setNode(ClusterNodeStatsSpec node) {
		this.node = node;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public StreamSessionNodeState getState() {
		return state;
	}

	public void setState(StreamSessionNodeState state) {
		this.state = state;
	}	
	
	
	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	
	public int getTickerCount() {
		return tickerCount;
	}

	public void setTickerCount(int tickerCount) {
		this.tickerCount = tickerCount;
	}

	public DTime getLastDataTickTime() {
		return lastDataTickTime;
	}

	public void setLastDataTickTime(DTime lastDataTickTime) {
		this.lastDataTickTime = lastDataTickTime;
	}

	@Transient
	public void addProblem(String problem) { 
		this.problems.add(problem);
	}
	
	@Transient
	public void addWarning(String warning) { 
		this.warnings.add(warning);
	}
	
	
	
	
	
	
}

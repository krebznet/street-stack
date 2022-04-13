package com.dunkware.trade.service.stream.json.controller.session;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamSessionStatsSpec {

	private String stream;
	private String sessionId;
	private StreamSessionState state;
	@JsonInclude(Include.NON_NULL)
	private DTime startTime; 
	@JsonInclude(Include.NON_NULL)
	private DTime startingTime;
	private StreamSessionStatus status;
	@JsonInclude(Include.NON_NULL)
	private int tickerCount;
	@JsonInclude(Include.NON_NULL)
	private int nodeTickerCount; 
	@JsonInclude(Include.NON_NULL)
	private List<String> problems = new ArrayList<String>();
	@JsonInclude(Include.NON_NULL)
	private List<String> warnings = new ArrayList<String>();
	@JsonInclude(Include.NON_NULL)
	private int skippedNodeCount;
	private int nodeCount; 
	@JsonInclude(Include.NON_NULL)
	private int startingNodeCount;
	@JsonInclude(Include.NON_NULL)
	private int exceptionNodeCount;
	@JsonInclude(Include.NON_NULL)
	private int yellowNodeCount; 
	@JsonInclude(Include.NON_NULL)
	private int runningNodeCount; 
	@JsonInclude(Include.NON_NULL)
	private int signalCount; 
	@JsonInclude(Include.NON_NULL)
	private long pendingTaskCount; 
	@JsonInclude(Include.NON_NULL)
	private long completedTaskCount;
	@JsonInclude(Include.NON_NULL)
	private long timeoutTaskCount;
	@JsonInclude(Include.NON_NULL)
	private int startupTime; 
	@JsonInclude(Include.NON_NULL)
	private String exception; 
	@JsonInclude(Include.NON_NULL)
	private int nodeProblemCount; 
	@JsonInclude(Include.NON_NULL)
	private int nodeWarningCount; 
	private List<StreamSessionNodeStatsSpec> nodes = new ArrayList<StreamSessionNodeStatsSpec>();
	
	public StreamSessionStatsSpec() { 
		
	}


	public String getStream() {
		return stream;
	}


	public void setStream(String stream) {
		this.stream = stream;
	}


	public DTime getStartTime() {
		return startTime;
	}


	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}


	public StreamSessionStatus getStatus() {
		return status;
	}


	public void setStatus(StreamSessionStatus status) {
		this.status = status;
	}


	public int getTickerCount() {
		return tickerCount;
	}


	public void setTickerCount(int tickerCount) {
		this.tickerCount = tickerCount;
	}


	public int getNodeCount() {
		return nodeCount;
	}


	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}


	public int getSignalCount() {
		return signalCount;
	}


	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
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


	public long getTimeoutTaskCount() {
		return timeoutTaskCount;
	}


	public void setTimeoutTaskCount(long timeoutTaskCount) {
		this.timeoutTaskCount = timeoutTaskCount;
	}


	public List<StreamSessionNodeStatsSpec> getNodes() {
		return nodes;
	}


	public void setNodes(List<StreamSessionNodeStatsSpec> nodes) {
		this.nodes = nodes;
	}


	public int getStartupTime() {
		return startupTime;
	}


	public void setStartupTime(int startupTime) {
		this.startupTime = startupTime;
	}


	public DTime getStartingTime() {
		return startingTime;
	}


	public void setStartingTime(DTime startingTime) {
		this.startingTime = startingTime;
	}


	public int getNodeTickerCount() {
		return nodeTickerCount;
	}


	public void setNodeTickerCount(int nodeTickerCount) {
		this.nodeTickerCount = nodeTickerCount;
	}


	public List<String> getProblems() {
		return problems;
	}


	public void setProblems(List<String> problems) {
		this.problems = problems;
	}


	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public int getStartingNodeCount() {
		return startingNodeCount;
	}
	

	public void setStartingNodeCount(int startingNodeCount) {
		this.startingNodeCount = startingNodeCount;
	}


	public int getExceptionNodeCount() {
		return exceptionNodeCount;
	}


	public void setExceptionNodeCount(int exceptionNodeCount) {
		this.exceptionNodeCount = exceptionNodeCount;
	}


	public int getYellowNodeCount() {
		return yellowNodeCount;
	}


	public void setYellowNodeCount(int yellowNodeCount) {
		this.yellowNodeCount = yellowNodeCount;
	}


	public int getRunningNodeCount() {
		return runningNodeCount;
	}


	public void setRunningNodeCount(int runningNodeCount) {
		this.runningNodeCount = runningNodeCount;
	}


	public StreamSessionState getState() {
		return state;
	}


	public void setState(StreamSessionState state) {
		this.state = state;
	}


	public String getException() {
		return exception;
	}


	public void setException(String exception) {
		this.exception = exception;
	}


	public List<String> getWarnings() {
		return warnings;
	}


	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	
	public int getNodeProblemCount() {
		return nodeProblemCount;
	}


	public void setNodeProblemCount(int nodeProblemCount) {
		this.nodeProblemCount = nodeProblemCount;
	}


	public int getNodeWarningCount() {
		return nodeWarningCount;
	}


	public void setNodeWarningCount(int nodeWarningCount) {
		this.nodeWarningCount = nodeWarningCount;
	}
	

	public int getSkippedNodeCount() {
		return skippedNodeCount;
	}


	public void setSkippedNodeCount(int skippedNodeCount) {
		this.skippedNodeCount = skippedNodeCount;
	}


	@Transient
	public void addProblem(String problem) { 
		this.problems.add(problem);
	}
	
	@Transient
	public void addWarning(String warning) { 
		this.warnings.add(warning);
	}
	
	@Transient
	public void incrementStartingNodes() { 
		this.startingNodeCount = this.startingNodeCount + 1;
	}
	
	@Transient
	public void decrementStartingNodes() { 
		this.startingNodeCount = this.startingNodeCount - 1;
	}
	
	@Transient
	public void incrementRunningNodes() { 
		this.runningNodeCount = this.runningNodeCount + 1;
	}

	@Transient
	public void decrementRunningNodes() { 
		this.runningNodeCount = this.runningNodeCount - 1;
	}
	@Transient
	public void incrementExceptionNodeCount() { 
		this.exceptionNodeCount = this.exceptionNodeCount + 1;
	}
	
	@Transient
	public void decrementExceptionNodeCount() { 
		this.exceptionNodeCount = exceptionNodeCount - 1;
	}
	
	
	
	
	
	
	
	
	
	
}

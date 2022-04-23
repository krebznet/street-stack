package com.dunkware.trade.service.stream.json.controller.session;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamSessionStatus {

	private String stream;
	private String sessionId;
	private StreamSessionState state;
	@JsonInclude(Include.NON_NULL)
	private DTime startTime; 
	@JsonInclude(Include.NON_NULL)
	private DTime startingTime;
	@JsonInclude(Include.NON_NULL)
	private int tickerCount;
	@JsonInclude(Include.NON_NULL)
	private int nodeTickerCount; 
	@JsonInclude(Include.NON_NULL)
	private List<String> problems = new ArrayList<String>();
	@JsonInclude(Include.NON_NULL)
	private List<String> warnings = new ArrayList<String>();
	@JsonInclude(Include.NON_NULL)
	private int exceptionNodeCount;
	private int nodeCount; 
	@JsonInclude(Include.NON_NULL)
	private int signalCount; 
	@JsonInclude(Include.NON_NULL)
	private long completedTasks;
	@JsonInclude(Include.NON_NULL)
	private long timeoutTasks;
	@JsonInclude(Include.NON_NULL)
	private int startupTime; 
	@JsonInclude(Include.NON_NULL)
	private String exception; 
	@JsonInclude(Include.NON_NULL)
	private List<StreamSessionNodeStatus> nodes = new ArrayList<StreamSessionNodeStatus>();
	
	public StreamSessionStatus() { 
		
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
	
	

	public int getExceptionNodeCount() {
		return exceptionNodeCount;
	}


	public void setExceptionNodeCount(int exceptionNodeCount) {
		this.exceptionNodeCount = exceptionNodeCount;
	}


	public long getCompletedTasks() {
		return completedTasks;
	}


	public void setCompletedTasks(long completedTasks) {
		this.completedTasks = completedTasks;
	}


	public long getTimeoutTasks() {
		return timeoutTasks;
	}


	public void setTimeoutTasks(long timeoutTasks) {
		this.timeoutTasks = timeoutTasks;
	}


	public List<StreamSessionNodeStatus> getNodes() {
		return nodes;
	}


	public void setNodes(List<StreamSessionNodeStatus> nodes) {
		this.nodes = nodes;
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

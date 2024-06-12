package com.dunkware.trade.service.stream.json.worker.stream;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamSessionWorkerStats {

	private String nodeId; 
	private String stream; 
	private String startException;
	private long pendingTaskCount; 
	private long completedTaskCount; 
	private long timeoutTaskCount; 
	private int signalCount; 
	@JsonInclude(Include.NON_NULL)
	private LocalTime streamTime; 
	@JsonInclude(Include.NON_NULL)
	private LocalTime systemTime; 
	private int rowCount; 
	private long tickCount; 
	private String workerId; 
	private int numericId;
	private StreamSessionWorkerStopState stopState;
	private long entitySnapshotCount = 0;
	
	private String status;

	@JsonInclude(Include.NON_NULL)
	private String requestError;
	
	private LocalTime lastDataTickTime;
	
	
	public StreamSessionWorkerStats() { 
		
	}


	public String getNodeId() {
		return nodeId;
	}


	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}


	public String getStream() {
		return stream;
	}


	public void setStream(String stream) {
		this.stream = stream;
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


	public LocalTime getStreamTime() {
		return streamTime;
	}


	public void setStreamTime(LocalTime streamTime) {
		this.streamTime = streamTime;
	}


	public LocalTime getSystemTime() {
		return systemTime;
	}


	public void setSystemTime(LocalTime systemTime) {
		this.systemTime = systemTime;
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


	public long getTickCount() {
		return tickCount;
	}


	public void setTickCount(long tickCount) {
		this.tickCount = tickCount;
	}


	public int getSignalCount() {
		return signalCount;
	}


	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}


	public LocalTime getLastDataTickTime() {
		return lastDataTickTime;
	}


	public void setLastDataTickTime(LocalTime lastDataTickTime) {
		this.lastDataTickTime = lastDataTickTime;
	}


	public String getRequestError() {
		return requestError;
	}


	public void setRequestError(String requestError) {
		this.requestError = requestError;
	}


	

	public String getWorkerId() {
		return workerId;
	}


	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}


	public int getNumericId() {
		return numericId;
	}


	public void setNumericId(int numericId) {
		this.numericId = numericId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStartException() {
		return startException;
	}


	public void setStartException(String startException) {
		this.startException = startException;
	}


	public StreamSessionWorkerStopState getStopState() {
		return stopState;
	}


	public void setStopState(StreamSessionWorkerStopState stopState) {
		this.stopState = stopState;
	}


	public long getEntitySnapshotCount() {
		return entitySnapshotCount;
	}


	public void setEntitySnapshotCount(long entitySnapshotCount) {
		this.entitySnapshotCount = entitySnapshotCount;
	}
	
	



	
	



	
	
	

	
	
	
	
	
	
	
	
	
	
	
}

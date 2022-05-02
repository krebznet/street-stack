package com.dunkware.trade.service.stream.json.worker.stream;

import com.dunkware.common.util.dtime.DTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamSessionWorkerStats {

	private String nodeId; 
	private String stream; 
	private long pendingTaskCount; 
	private long completedTaskCount; 
	private long timeoutTaskCount; 
	private int signalCount; 
	@JsonInclude(Include.NON_NULL)
	private DTime streamTime; 
	@JsonInclude(Include.NON_NULL)
	private DTime systemTime; 
	private int rowCount; 
	private long tickCount; 

	@JsonInclude(Include.NON_NULL)
	private String requestError;
	
	private DTime lastDataTickTime;
	
	
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


	public DTime getLastDataTickTime() {
		return lastDataTickTime;
	}


	public void setLastDataTickTime(DTime lastDataTickTime) {
		this.lastDataTickTime = lastDataTickTime;
	}


	public String getRequestError() {
		return requestError;
	}


	public void setRequestError(String requestError) {
		this.requestError = requestError;
	}
	
	
	
	
	
	
	
	
	
	
	
}

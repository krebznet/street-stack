package com.dunkware.trade.service.stream.protocol.capture.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class StreamCaptureSpec {

	private String stream; 
	private DDate date; 
	private DTime startTime; 
	private DTime stopTime; 
	private int partitionCount; 
	private String exception; 
	private StreamCaptureStatus status; 
	
	private List<StreamCapturePartitionSpec> partitions = new ArrayList<StreamCapturePartitionSpec>();

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public DDate getDate() {
		return date;
	}

	public void setDate(DDate date) {
		this.date = date;
	}

	public DTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}

	public DTime getStopTime() {
		return stopTime;
	}

	public void setStopTime(DTime stopTime) {
		this.stopTime = stopTime;
	}

	public int getPartitionCount() {
		return partitionCount;
	}

	public void setPartitionCount(int partitionCount) {
		this.partitionCount = partitionCount;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public StreamCaptureStatus getStatus() {
		return status;
	}

	public void setStatus(StreamCaptureStatus status) {
		this.status = status;
	}

	public List<StreamCapturePartitionSpec> getPartitions() {
		return partitions;
	}

	public void setPartitions(List<StreamCapturePartitionSpec> partitions) {
		this.partitions = partitions;
	}
	
	
}

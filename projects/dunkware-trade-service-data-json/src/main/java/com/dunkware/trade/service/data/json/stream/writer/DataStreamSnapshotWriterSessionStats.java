package com.dunkware.trade.service.data.json.stream.writer;

import com.dunkware.common.util.dtime.DDateTime;

public class DataStreamSnapshotWriterSessionStats {
	
	private DDateTime startMessageDateTime; 
	private DDateTime stopMessageDateTime;
	private DDateTime startSessionDateTime;  
	private DDateTime stopSessionDateTime;
	
	private long entityCount; 
	private long writeCount; 
	private long queueSize; 
	private double writeTime; 
	private int writeSize;
	
	public DDateTime getStartMessageDateTime() {
		return startMessageDateTime;
	}
	public void setStartMessageDateTime(DDateTime startMessageDateTime) {
		this.startMessageDateTime = startMessageDateTime;
	}
	public DDateTime getStopMessageDateTime() {
		return stopMessageDateTime;
	}
	public void setStopMessageDateTime(DDateTime stopMessageDateTime) {
		this.stopMessageDateTime = stopMessageDateTime;
	}
	public DDateTime getStartSessionDateTime() {
		return startSessionDateTime;
	}
	public void setStartSessionDateTime(DDateTime startSessionDateTime) {
		this.startSessionDateTime = startSessionDateTime;
	}
	public DDateTime getStopSessionDateTime() {
		return stopSessionDateTime;
	}
	public void setStopSessionDateTime(DDateTime stopSessionDateTime) {
		this.stopSessionDateTime = stopSessionDateTime;
	}
	public long getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(long entityCount) {
		this.entityCount = entityCount;
	}
	public long getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(long writeCount) {
		this.writeCount = writeCount;
	}
	public long getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(long queueSize) {
		this.queueSize = queueSize;
	}
	public double getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(double writeTime) {
		this.writeTime = writeTime;
	}
	public int getWriteSize() {
		return writeSize;
	}
	public void setWriteSize(int writeSize) {
		this.writeSize = writeSize;
	} 
	
	
	

}

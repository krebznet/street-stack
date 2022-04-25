package com.dunkware.trade.service.data.json.stream.writer;

import com.dunkware.common.util.dtime.DDateTime;

public class DataStreamSignalWriterSessionStats {
	
	private int signalWriteCount; 
	private int signalConsumeCount; 
	private int writeQueueSize;
	private DDateTime lastSignalWriteTime;
	public int getSignalWriteCount() {
		return signalWriteCount;
	}
	public void setSignalWriteCount(int signalWriteCount) {
		this.signalWriteCount = signalWriteCount;
	}
	public int getSignalConsumeCount() {
		return signalConsumeCount;
	}
	public void setSignalConsumeCount(int signalConsumeCount) {
		this.signalConsumeCount = signalConsumeCount;
	}
	public int getWriteQueueSize() {
		return writeQueueSize;
	}
	public void setWriteQueueSize(int writeQueueSize) {
		this.writeQueueSize = writeQueueSize;
	}
	public DDateTime getLastSignalWriteTime() {
		return lastSignalWriteTime;
	}
	public void setLastSignalWriteTime(DDateTime lastSignalWriteTime) {
		this.lastSignalWriteTime = lastSignalWriteTime;
	} 
	
	
	
	
	
	
}

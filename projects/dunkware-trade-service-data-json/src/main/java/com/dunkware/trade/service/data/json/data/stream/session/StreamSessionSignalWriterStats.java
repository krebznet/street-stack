package com.dunkware.trade.service.data.json.data.stream.session;

public class StreamSessionSignalWriterStats {
	
	private long consumeCount; 
	private long writeCount; 
	private double lastWriteTime; 
	private int lastWriteSize;
	
	public long getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(long consumeCount) {
		this.consumeCount = consumeCount;
	}
	public long getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(long writeCount) {
		this.writeCount = writeCount;
	}
	public double getLastWriteTime() {
		return lastWriteTime;
	}
	public void setLastWriteTime(double lastWriteTime) {
		this.lastWriteTime = lastWriteTime;
	}
	public int getLastWriteSize() {
		return lastWriteSize;
	}
	public void setLastWriteSize(int lastWriteSize) {
		this.lastWriteSize = lastWriteSize;
	} 

}

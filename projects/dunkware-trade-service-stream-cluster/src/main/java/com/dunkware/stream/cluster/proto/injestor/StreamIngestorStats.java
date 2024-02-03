package com.dunkware.stream.cluster.proto.injestor;

public class StreamIngestorStats {
	
	private String name = null;
	private long writeCount = 0;
	private int queueSize = 0;
	private int writeExceptions = 0;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(long writeCount) {
		this.writeCount = writeCount;
	}
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	public int getWriteExceptions() {
		return writeExceptions;
	}
	public void setWriteExceptions(int writeExceptions) {
		this.writeExceptions = writeExceptions;
	}
	
	

}

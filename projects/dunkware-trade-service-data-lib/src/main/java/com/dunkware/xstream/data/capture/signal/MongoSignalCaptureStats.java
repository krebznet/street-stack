package com.dunkware.xstream.data.capture.signal;

public class MongoSignalCaptureStats {
	
	private int queueSize; 
	private int writeCount;
	private int consumeCount = 0;
	private String lastSignalTime; 
	private double lastBulkWriteTime; 
	private int errorCount; 
	private int pauseCount; 
	
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	public int getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(int writeCount) {
		this.writeCount = writeCount;
	}
	public String getLastSignalTime() {
		return lastSignalTime;
	}
	public void setLastSignalTime(String lastSignalTime) {
		this.lastSignalTime = lastSignalTime;
	}
	
	public double getLastBulkWriteTime() {
		return lastBulkWriteTime;
	}
	public void setLastBulkWriteTime(double lastBulkWriteTime) {
		this.lastBulkWriteTime = lastBulkWriteTime;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public int getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}
	public int getPauseCount() {
		return pauseCount;
	}
	public void setPauseCount(int pauseCount) {
		this.pauseCount = pauseCount;
	} 
	
	

	
	
	
	
	
	

}

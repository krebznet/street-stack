package com.dunkware.xstream.data.capture.snapshot;

public class MongoSnapshotCaptureStats {
	
	private String captureId; 
	private String stream; 
	private long recordCount = 0;
	private long writeCount = 0; 
	private long queueSize = 0; 
	private double lastWriteTime = 0;
	private int bucketWriteCount = 0;
	private int snapshotWriteCount = 0;
	private int errorLogCount = 0; 
	
	public String getCaptureId() {
		return captureId;
	}
	public void setCaptureId(String captureId) {
		this.captureId = captureId;
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
	public double getLastWriteTime() {
		return lastWriteTime;
	}
	public void setLastWriteTime(double lastWriteTime) {
		this.lastWriteTime = lastWriteTime;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public int getBucketWriteCount() {
		return bucketWriteCount;
	}
	public void setBucketWriteCount(int bucketWriteCount) {
		this.bucketWriteCount = bucketWriteCount;
	}
	public int getSnapshotWriteCount() {
		return snapshotWriteCount;
	}
	public void setSnapshotWriteCount(int snapshotWriteCount) {
		this.snapshotWriteCount = snapshotWriteCount;
	}
	public int getErrorLogCount() {
		return errorLogCount;
	}
	public void setErrorLogCount(int errorLogCount) {
		this.errorLogCount = errorLogCount;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	
	
	
	
	
	
	
	
	

}

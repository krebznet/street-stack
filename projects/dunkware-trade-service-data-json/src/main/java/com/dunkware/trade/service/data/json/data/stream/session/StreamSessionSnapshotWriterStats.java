package com.dunkware.trade.service.data.json.data.stream.session;

public class StreamSessionSnapshotWriterStats {

	private long snapshotCount;
	private long bucketWriteCount;
	private int bucketWriteSize;
	private double bucketWriteTime;
	private int queueSize;
	private int pauseCount;
	public long getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public long getBucketWriteCount() {
		return bucketWriteCount;
	}
	public void setBucketWriteCount(long bucketWriteCount) {
		this.bucketWriteCount = bucketWriteCount;
	}
	public int getBucketWriteSize() {
		return bucketWriteSize;
	}
	public void setBucketWriteSize(int bucketWriteSize) {
		this.bucketWriteSize = bucketWriteSize;
	}
	public double getBucketWriteTime() {
		return bucketWriteTime;
	}
	public void setBucketWriteTime(double bucketWriteTime) {
		this.bucketWriteTime = bucketWriteTime;
	}
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	public int getPauseCount() {
		return pauseCount;
	}
	public void setPauseCount(int pauseCount) {
		this.pauseCount = pauseCount;
	}
	
	

}

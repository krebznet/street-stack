package com.dunkware.trade.service.data.json.stream.session;

public class DataStreamSessionSnapshotWriterStats {

	private int pauseCount;
	private int pauseTime;
	private long snapshotConsumeCount;
	private long snapshotWriteCount; 
	private long snapshotBucketWriteCount;
	private int entityCount;
	private double lastBucketWriteSpeed; 
	private double maxBucketWriteSpeed; 
	private String sessionIdentifier;
	public int getPauseCount() {
		return pauseCount;
	}
	public void setPauseCount(int pauseCount) {
		this.pauseCount = pauseCount;
	}
	public int getPauseTime() {
		return pauseTime;
	}
	public void setPauseTime(int pauseTime) {
		this.pauseTime = pauseTime;
	}
	public long getSnapshotConsumeCount() {
		return snapshotConsumeCount;
	}
	public void setSnapshotConsumeCount(long snapshotConsumeCount) {
		this.snapshotConsumeCount = snapshotConsumeCount;
	}
	public long getSnapshotWriteCount() {
		return snapshotWriteCount;
	}
	public void setSnapshotWriteCount(long snapshotWriteCount) {
		this.snapshotWriteCount = snapshotWriteCount;
	}
	public long getSnapshotBucketWriteCount() {
		return snapshotBucketWriteCount;
	}
	public void setSnapshotBucketWriteCount(long snapshotBucketWriteCount) {
		this.snapshotBucketWriteCount = snapshotBucketWriteCount;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public double getLastBucketWriteSpeed() {
		return lastBucketWriteSpeed;
	}
	public void setLastBucketWriteSpeed(double lastBucketWriteSpeed) {
		this.lastBucketWriteSpeed = lastBucketWriteSpeed;
	}
	public double getMaxBucketWriteSpeed() {
		return maxBucketWriteSpeed;
	}
	public void setMaxBucketWriteSpeed(double maxBucketWriteSpeed) {
		this.maxBucketWriteSpeed = maxBucketWriteSpeed;
	}
	public String getSessionIdentifier() {
		return sessionIdentifier;
	}
	public void setSessionIdentifier(String sessionIdentifier) {
		this.sessionIdentifier = sessionIdentifier;
	} 
	
	
	
	
}

package com.dunkware.trade.service.data.json.stream.session;

import com.dunkware.common.util.dtime.DTime;

public class DataStreamSessionSnapshotWriterStats {

	private int pauseCount;
	private double pauseTime;
	private long snapshotConsumeCount;
	private long snapshotWriteCount; 
	private long snapshotBucketWriteCount;
	private int entityCount;
	private double lastBucketWriteSpeed; 
	private double maxBucketWriteSpeed; 
	private String sessionIdentifier;
	private String mongoURL; 
	private String mongoDatabase; 
	private String mongoCollection; 
	
	private DTime lastSnapshotTime;
	private DTime lastSnapshotWriteTime; 
	
	public int getPauseCount() {
		return pauseCount;
	}
	public void setPauseCount(int pauseCount) {
		this.pauseCount = pauseCount;
	}
	
	
	public double getPauseTime() {
		return pauseTime;
	}
	public void setPauseTime(double pauseTime) {
		this.pauseTime = pauseTime;
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
	public String getMongoURL() {
		return mongoURL;
	}
	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}
	public String getMongoDatabase() {
		return mongoDatabase;
	}
	public void setMongoDatabase(String mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}
	public String getMongoCollection() {
		return mongoCollection;
	}
	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
	}
	public DTime getLastSnapshotTime() {
		return lastSnapshotTime;
	}
	public void setLastSnapshotTime(DTime lastSnapshotTime) {
		this.lastSnapshotTime = lastSnapshotTime;
	}
	public DTime getLastSnapshotWriteTime() {
		return lastSnapshotWriteTime;
	}
	public void setLastSnapshotWriteTime(DTime lastSnapshotWriteTime) {
		this.lastSnapshotWriteTime = lastSnapshotWriteTime;
	} 
	
	
	
	
	
}

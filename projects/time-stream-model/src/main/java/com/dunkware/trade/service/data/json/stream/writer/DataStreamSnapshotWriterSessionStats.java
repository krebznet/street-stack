package com.dunkware.trade.service.data.json.stream.writer;

public class DataStreamSnapshotWriterSessionStats {
	
	private long entityCount;
	private long snapshotWriteCount;
	private long snapshotConsumeCount; 
	private long queueSize; 
	private String lastWriteStreamTime; 
	private int lastWriteSize;
	private double lastWriteDuration;
	private int bucketCount; 
	private boolean stopSession = false;
	private int consumerPauseCount; 
	private double consumerPauseTime;
	private String mongoURL;
	private String mongoCollection; 
	private String mongoDatabase;
	private String startTime;
	private String stopTime; 
	private int problemCount; 
	private double writeLagTime;
	
	public long getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(long entityCount) {
		this.entityCount = entityCount;
	}
	public long getSnapshotWriteCount() {
		return snapshotWriteCount;
	}
	public void setSnapshotWriteCount(long snapshotWriteCount) {
		this.snapshotWriteCount = snapshotWriteCount;
	}
	public long getSnapshotConsumeCount() {
		return snapshotConsumeCount;
	}
	public void setSnapshotConsumeCount(long snapshotConsumeCount) {
		this.snapshotConsumeCount = snapshotConsumeCount;
	}
	public long getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(long queueSize) {
		this.queueSize = queueSize;
	}
	
	public int getLastWriteSize() {
		return lastWriteSize;
	}
	public void setLastWriteSize(int lastWriteSize) {
		this.lastWriteSize = lastWriteSize;
	}
	public double getLastWriteDuration() {
		return lastWriteDuration;
	}
	public void setLastWriteDuration(double lastWriteDuration) {
		this.lastWriteDuration = lastWriteDuration;
	}
	public int getBucketCount() {
		return bucketCount;
	}
	public void setBucketCount(int bucketCount) {
		this.bucketCount = bucketCount;
	}
	public boolean isStopSession() {
		return stopSession;
	}
	public void setStopSession(boolean stopSession) {
		this.stopSession = stopSession;
	}
	public int getConsumerPauseCount() {
		return consumerPauseCount;
	}
	public void setConsumerPauseCount(int consumerPauseCount) {
		this.consumerPauseCount = consumerPauseCount;
	}
	public double getConsumerPauseTime() {
		return consumerPauseTime;
	}
	public void setConsumerPauseTime(double consumerPauseTime) {
		this.consumerPauseTime = consumerPauseTime;
	}
	public String getMongoURL() {
		return mongoURL;
	}
	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}
	public String getMongoCollection() {
		return mongoCollection;
	}
	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
	}
	public String getMongoDatabase() {
		return mongoDatabase;
	}
	public void setMongoDatabase(String mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}
	public int getProblemCount() {
		return problemCount;
	}
	public void setProblemCount(int problemCount) {
		this.problemCount = problemCount;
	}
	
	public double getWriteLagTime() {
		return writeLagTime;
	}
	public void setWriteLagTime(double writeLagTime) {
		this.writeLagTime = writeLagTime;
	}
	public String getLastWriteStreamTime() {
		return lastWriteStreamTime;
	}
	public void setLastWriteStreamTime(String lastWriteStreamTime) {
		this.lastWriteStreamTime = lastWriteStreamTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package com.dunkware.trade.service.stream.data;

import com.dunkware.common.util.dtime.DDateTime;

public class DataStreamSnapshotWriterSessionStats2 {

	private long entitiyCount;
	private long inserteCount;
	private long consumCount;
	private long consumeQueue;
	private long writeQueue; 
	private DDateTime lastWriteTime; 
	private int lastWriteSize;
	private double lastWriteSeconds;
	private int consumerPauseCount; 
	private double consumerPauseTime;
	private DDateTime startTime;
	private int problemCount;
	
	public long getEntitiyCount() {
		return entitiyCount;
	}
	public void setEntitiyCount(long entitiyCount) {
		this.entitiyCount = entitiyCount;
	}
	public long getInserteCount() {
		return inserteCount;
	}
	public void setInserteCount(long inserteCount) {
		this.inserteCount = inserteCount;
	}
	public long getConsumCount() {
		return consumCount;
	}
	public void setConsumCount(long consumCount) {
		this.consumCount = consumCount;
	}
	public long getConsumeQueue() {
		return consumeQueue;
	}
	public void setConsumeQueue(long consumeQueue) {
		this.consumeQueue = consumeQueue;
	}
	public long getWriteQueue() {
		return writeQueue;
	}
	public void setWriteQueue(long writeQueue) {
		this.writeQueue = writeQueue;
	}
	public DDateTime getLastWriteTime() {
		return lastWriteTime;
	}
	public void setLastWriteTime(DDateTime lastWriteTime) {
		this.lastWriteTime = lastWriteTime;
	}
	public int getLastWriteSize() {
		return lastWriteSize;
	}
	public void setLastWriteSize(int lastWriteSize) {
		this.lastWriteSize = lastWriteSize;
	}
	public double getLastWriteSeconds() {
		return lastWriteSeconds;
	}
	public void setLastWriteSeconds(double lastWriteSeconds) {
		this.lastWriteSeconds = lastWriteSeconds;
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
	public DDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DDateTime startTime) {
		this.startTime = startTime;
	}
	public int getProblemCount() {
		return problemCount;
	}
	public void setProblemCount(int problemCount) {
		this.problemCount = problemCount;
	} 
	
	
	
	
	
}

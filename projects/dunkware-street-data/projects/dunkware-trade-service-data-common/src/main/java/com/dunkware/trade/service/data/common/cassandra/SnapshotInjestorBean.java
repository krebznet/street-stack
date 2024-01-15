package com.dunkware.trade.service.data.common.cassandra;

public class SnapshotInjestorBean {
	
	private long queueSize; 
	private long writeCount; 
	private long varCount; 
	private long signalCount;
	private double writeSecond;
	
	public long getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(long queueSize) {
		this.queueSize = queueSize;
	}
	public long getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(long writeCount) {
		this.writeCount = writeCount;
	}
	public long getVarCount() {
		return varCount;
	}
	public void setVarCount(long varCount) {
		this.varCount = varCount;
	}
	public long getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(long signalCount) {
		this.signalCount = signalCount;
	}
	public double getWriteSecond() {
		return writeSecond;
	}
	public void setWriteSecond(double writeSecond) {
		this.writeSecond = writeSecond;
	}
	
	
	

}

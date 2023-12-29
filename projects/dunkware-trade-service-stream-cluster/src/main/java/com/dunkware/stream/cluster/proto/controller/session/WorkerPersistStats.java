package com.dunkware.stream.cluster.proto.controller.session;

public class WorkerPersistStats {

	private long varSnapshotCount = 0; 
	private long varSnapshotQueue = 0; 
	private double varSnapshotSecondTime = 0.0;
	private long varSnapshotSecondCount = 0;
	private long varSnapshotFirstCaptureTime = 0;
	private long varSnapshotLastCaptureTime = 0;
	private String stream;

	public long getVarSnapshotCount() {
		return varSnapshotCount;
	}

	public void setVarSnapshotCount(long varSnapshotCount) {
		this.varSnapshotCount = varSnapshotCount;
	}

	public long getVarSnapshotQueue() {
		return varSnapshotQueue;
	}

	public void setVarSnapshotQueue(long varSnapshotQueue) {
		this.varSnapshotQueue = varSnapshotQueue;
	}

	public double getVarSnapshotSecondTime() {
		return varSnapshotSecondTime;
	}

	public void setVarSnapshotSecondTime(double varSnapshotSecondTime) {
		this.varSnapshotSecondTime = varSnapshotSecondTime;
	}

	public long getVarSnapshotSecondCount() {
		return varSnapshotSecondCount;
	}

	public void setVarSnapshotSecondCount(long varSnapshotSecondCount) {
		this.varSnapshotSecondCount = varSnapshotSecondCount;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public long getVarSnapshotFirstCaptureTime() {
		return varSnapshotFirstCaptureTime;
	}

	public void setVarSnapshotFirstCaptureTime(long varSnapshotFirstCaptureTime) {
		this.varSnapshotFirstCaptureTime = varSnapshotFirstCaptureTime;
	}

	public long getVarSnapshotLastCaptureTime() {
		return varSnapshotLastCaptureTime;
	}

	public void setVarSnapshotLastCaptureTime(long varSnapshotLastCaptureTime) {
		this.varSnapshotLastCaptureTime = varSnapshotLastCaptureTime;
	}
	
	
	
	
	
	
	
	
			
	
}

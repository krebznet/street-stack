package com.dunkware.trade.service.data.json.server.container;

public class DataStreamContainerStats {
	
	private DataStreamContainerState state; 
	private String lastSnapshot = null; 
	private String lastSignal = null; 
	private String lastTime = null;
	private int snapshotCount = 0;
	private int signalCount = 0;
	private int timeCount = 0;
	private String stream;
	
	public DataStreamContainerState getState() {
		return state;
	}
	public void setState(DataStreamContainerState state) {
		this.state = state;
	}
	public String getLastSnapshot() {
		return lastSnapshot;
	}
	public void setLastSnapshot(String lastSnapshot) {
		this.lastSnapshot = lastSnapshot;
	}
	public String getLastSignal() {
		return lastSignal;
	}
	public void setLastSignal(String lastSignal) {
		this.lastSignal = lastSignal;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(int snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public int getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	} 
	
	

}

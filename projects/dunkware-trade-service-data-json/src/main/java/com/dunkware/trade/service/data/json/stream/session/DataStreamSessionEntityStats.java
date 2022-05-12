package com.dunkware.trade.service.data.json.stream.session;

public class DataStreamSessionEntityStats {

	private int id; 
	private String ident; 
	private int snapshotCount; 
	private int signalCount; 
	private String lastSnapshot; 
	private String lastSignal;
	private String createdTime; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
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
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	} 
	
	
	
	
}

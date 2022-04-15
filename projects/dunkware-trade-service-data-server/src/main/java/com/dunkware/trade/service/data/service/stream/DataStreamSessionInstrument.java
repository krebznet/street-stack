package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDateTime;

import com.dunkware.trade.service.data.service.repository.DataStreamSessionEntity;

// then we will update these and mark
// them dirty as updated. 
public class DataStreamSessionInstrument {
	
	private int id; 
	private String identifier; 
	
	private volatile long snapshotCount; 
	private volatile long signalCount; 
	private volatile int errorCount; 
	private LocalDateTime firstSnapshot;
	
	private DataStreamSessionEntity entity; 
	private DataStreamSession sesion; 
	
	private boolean dirty = false; 
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
		dirty = true;
	}
	public long getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	
	public void snapshot() { 
		
	}
	
	
	public long getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(long signalCount) {
		this.signalCount = signalCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public LocalDateTime getFirstSnapshot() {
		return firstSnapshot;
	}
	public void setFirstSnapshot(LocalDateTime firstSnapshot) {
		this.firstSnapshot = firstSnapshot;
	} 

	
}

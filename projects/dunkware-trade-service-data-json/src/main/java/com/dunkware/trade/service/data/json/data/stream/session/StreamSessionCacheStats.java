package com.dunkware.trade.service.data.json.data.stream.session;

public class StreamSessionCacheStats {
	
	private int signalCount; 
	private int snapshotCount; 
	private int entityCount;
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(int snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	} 
	
	

}

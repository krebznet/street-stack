package com.dunkware.trade.service.data.json.stream.session;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSignalWriterSessionStats;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterSessionStats2;

public class DataStreamSessionStats {
	
	private String stream; 
	private String identifier; 
	private DDateTime sessionTime; 
	private DDateTime lastSnapshotWriteTime; 
	private double snapshotWriteLagTime; 
	private DataStreamSessionState state; 
	private DataStreamSignalWriterSessionStats signalStats;
	private DataStreamSnapshotWriterSessionStats2 snapshotStats;
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public DDateTime getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(DDateTime sessionTime) {
		this.sessionTime = sessionTime;
	}
	public DDateTime getLastSnapshotWriteTime() {
		return lastSnapshotWriteTime;
	}
	public void setLastSnapshotWriteTime(DDateTime lastSnapshotWriteTime) {
		this.lastSnapshotWriteTime = lastSnapshotWriteTime;
	}
	public double getSnapshotWriteLagTime() {
		return snapshotWriteLagTime;
	}
	public void setSnapshotWriteLagTime(double snapshotWriteLagTime) {
		this.snapshotWriteLagTime = snapshotWriteLagTime;
	}
	public DataStreamSessionState getState() {
		return state;
	}
	public void setState(DataStreamSessionState state) {
		this.state = state;
	}
	public DataStreamSignalWriterSessionStats getSignalStats() {
		return signalStats;
	}
	public void setSignalStats(DataStreamSignalWriterSessionStats signalStats) {
		this.signalStats = signalStats;
	}
	public DataStreamSnapshotWriterSessionStats2 getSnapshotStats() {
		return snapshotStats;
	}
	public void setSnapshotStats(DataStreamSnapshotWriterSessionStats2 snapshotStats) {
		this.snapshotStats = snapshotStats;
	}
	
	
	
	
}

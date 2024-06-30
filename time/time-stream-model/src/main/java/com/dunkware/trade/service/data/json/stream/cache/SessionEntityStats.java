package com.dunkware.trade.service.data.json.stream.cache;

import java.util.ArrayList;
import java.util.List;

public class SessionEntityStats {
	
	// session id -- good question
	private String sessionId; 
	private String identifier; 
	private int id; 
	private long snapshotCount; 
	
	private List<SessionEntitySignalStats> signalStats = new ArrayList<SessionEntitySignalStats>();
	private List<SessionEntityVarStats> varStats = new ArrayList<SessionEntityVarStats>();
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public List<SessionEntitySignalStats> getSignalStats() {
		return signalStats;
	}
	public void setSignalStats(List<SessionEntitySignalStats> signalStats) {
		this.signalStats = signalStats;
	}
	public List<SessionEntityVarStats> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<SessionEntityVarStats> varStats) {
		this.varStats = varStats;
	}
	
	
	
	

}

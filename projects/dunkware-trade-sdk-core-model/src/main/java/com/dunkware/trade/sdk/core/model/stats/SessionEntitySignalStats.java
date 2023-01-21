package com.dunkware.trade.sdk.core.model.stats;

import java.util.ArrayList;
import java.util.List;

public class SessionEntitySignalStats {
	
	private List<SessionEntityStats> entityStats = new ArrayList<SessionEntityStats>();
	private List<SessionSignal> signalStats = new ArrayList<SessionSignal>();
	
	public List<SessionEntityStats> getEntityStats() {
		return entityStats;
	}
	public void setEntityStats(List<SessionEntityStats> entityStats) {
		this.entityStats = entityStats;
	}
	public List<SessionSignal> getSignalStats() {
		return signalStats;
	}
	public void setSignalStats(List<SessionSignal> signalStats) {
		this.signalStats = signalStats;
	}

	
	
	
}

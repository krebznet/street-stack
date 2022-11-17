package com.dunkware.trade.sdk.core.model.stats;

import java.util.ArrayList;
import java.util.List;

public class SessionStats {
	
	private List<EntitySessionStats> entityStats = new ArrayList<EntitySessionStats>();
	private List<SignalSesionStats> signalStats = new ArrayList<SignalSesionStats>();
	
	public List<EntitySessionStats> getEntityStats() {
		return entityStats;
	}
	public void setEntityStats(List<EntitySessionStats> entityStats) {
		this.entityStats = entityStats;
	}
	public List<SignalSesionStats> getSignalStats() {
		return signalStats;
	}
	public void setSignalStats(List<SignalSesionStats> signalStats) {
		this.signalStats = signalStats;
	}

	
	
	
}

package com.dunkware.xstream.model.stats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;

public class StreamStats {
	
	private String streamIdentifier; 
	private DDate date; 
	
	private List<EntityStats> entityStats = new ArrayList<EntityStats>();
	private List<SignalStats> signalStats = new ArrayList<SignalStats>();
	
	public List<EntityStats> getEntityStats() {
		return entityStats;
	}
	
	public void setEntityStats(List<EntityStats> entityStats) {
		this.entityStats = entityStats;
	}
	
	public List<SignalStats> getSignalStats() {
		return signalStats;
	}
	
	public void setSignalStats(List<SignalStats> signalStats) {
		this.signalStats = signalStats;
	}
	
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}

	public DDate getDate() {
		return date;
	}

	public void setDate(DDate date) {
		this.date = date;
	}
	
	
	
	
	
	

	
	
	
}

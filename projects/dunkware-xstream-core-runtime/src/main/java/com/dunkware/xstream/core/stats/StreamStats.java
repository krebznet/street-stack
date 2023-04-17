package com.dunkware.xstream.core.stats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.stats.EntityStatsSession;

public class StreamStats {
	
	private List<EntityStatsSession> entities = new ArrayList<EntityStatsSession>();
	private String streamIdent;
	
	public List<EntityStatsSession> getEntities() {
		return entities;
	}
	public void setEntities(List<EntityStatsSession> entities) {
		this.entities = entities;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	} 
	
	
}

package com.dunkware.xstream.model.stats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.stats.session.EntitySessionStats;

public class StreamStatsPayload {
	
	private List<EntitySessionStats> entities = new ArrayList<EntitySessionStats>();
	private String streamIdent;
	
	public List<EntitySessionStats> getEntities() {
		return entities;
	}
	public void setEntities(List<EntitySessionStats> entities) {
		this.entities = entities;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	} 
	
	
}

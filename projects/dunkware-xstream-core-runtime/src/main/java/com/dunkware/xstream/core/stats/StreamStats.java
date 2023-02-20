package com.dunkware.xstream.core.stats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.stats.StreamEntityDayStats;

public class StreamStats {
	
	private List<StreamEntityDayStats> entities = new ArrayList<StreamEntityDayStats>();
	private String streamIdent;
	
	public List<StreamEntityDayStats> getEntities() {
		return entities;
	}
	public void setEntities(List<StreamEntityDayStats> entities) {
		this.entities = entities;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	} 
	
	
}

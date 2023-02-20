package com.dunkware.xstream.model.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StreamEntityDayStats {
	
	private LocalDate date;
	private int entityId; 
	private String entityIdent; 
	private int streamId; 
	private String streamIdent;
	
	private List<StreamVariableStats> variables = new ArrayList<StreamVariableStats>();
	private List<StreamSignalStats> signals = new ArrayList<StreamSignalStats>();
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getEntityIdent() {
		return entityIdent;
	}
	public void setEntityIdent(String entityIdent) {
		this.entityIdent = entityIdent;
	}
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	public List<StreamVariableStats> getVariables() {
		return variables;
	}
	public void setVariables(List<StreamVariableStats> variables) {
		this.variables = variables;
	}
	public List<StreamSignalStats> getSignals() {
		return signals;
	}
	public void setSignals(List<StreamSignalStats> signals) {
		this.signals = signals;
	} 
	
	
	
	
	

}

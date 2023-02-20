package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dunkware.xstream.model.stats.StreamSignalStats;
import com.dunkware.xstream.model.stats.StreamVariableStats;

@Document(collection = "stream_stats_entity_day")
public class StreamEntityDayStatsDoc {

	@Id
	private long id; 
	
	private LocalDate date; 
	private int streamId;
	private String streamIdent; 
	private int entityId; 
	private String entityIdent; 
	private double streamVersion; 
	private List<StreamVariableStats> variables = new ArrayList<StreamVariableStats>();
	private List<StreamSignalStats> signals = new ArrayList<StreamSignalStats>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
	public double getStreamVersion() {
		return streamVersion;
	}
	public void setStreamVersion(double streamVersion) {
		this.streamVersion = streamVersion;
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

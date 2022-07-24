package com.dunkware.xstream.model.metrics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EntitySessionMetricsDTO {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private int entityId;
	private int entityIdent; 
	
	private List<EntitySessionVarMetrics> vars = new ArrayList<EntitySessionVarMetrics>();
	private List<EntitySessionSignalMetrics> signals = new ArrayList<EntitySessionSignalMetrics>();
	
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
	public int getEntityIdent() {
		return entityIdent;
	}
	public void setEntityIdent(int entityIdent) {
		this.entityIdent = entityIdent;
	}
	public List<EntitySessionVarMetrics> getVars() {
		return vars;
	}
	public void setVars(List<EntitySessionVarMetrics> vars) {
		this.vars = vars;
	}
	public List<EntitySessionSignalMetrics> getSignals() {
		return signals;
	}
	public void setSignals(List<EntitySessionSignalMetrics> signals) {
		this.signals = signals;
	}
	
	
}

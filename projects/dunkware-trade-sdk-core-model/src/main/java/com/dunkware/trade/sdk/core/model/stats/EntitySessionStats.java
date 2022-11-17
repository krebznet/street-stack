package com.dunkware.trade.sdk.core.model.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntitySessionStats {

	private LocalDate date;
	
	private int id; 
	private String ident; 
	
	private List<EntitySessionVarStats> varStats = new ArrayList<EntitySessionVarStats>();
	private List<SignalSesionStats> sigStats = new ArrayList<SignalSesionStats>();
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public List<EntitySessionVarStats> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<EntitySessionVarStats> varStats) {
		this.varStats = varStats;
	}
	public List<SignalSesionStats> getSigStats() {
		return sigStats;
	}
	public void setSigStats(List<SignalSesionStats> sigStats) {
		this.sigStats = sigStats;
	}
	
	
	
}

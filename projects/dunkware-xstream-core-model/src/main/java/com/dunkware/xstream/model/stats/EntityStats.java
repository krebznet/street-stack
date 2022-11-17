package com.dunkware.xstream.model.stats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;

public class EntityStats {

	private DDate date;
	
	private int id; 
	private String ident; 
	
	private List<EntityVarStats> varStats = new ArrayList<EntityVarStats>();
	private List<SignalStats> sigStats = new ArrayList<SignalStats>();
	
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
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
	public List<EntityVarStats> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<EntityVarStats> varStats) {
		this.varStats = varStats;
	}
	public List<SignalStats> getSigStats() {
		return sigStats;
	}
	public void setSigStats(List<SignalStats> sigStats) {
		this.sigStats = sigStats;
	}
	
	
	
	
	
}

package com.dunkware.trade.sdk.core.model.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionEntityStats {

	private LocalDate date;
	
	private int id; 
	private String ident; 
	
	private List<SessionEntityVarStats> varStats = new ArrayList<SessionEntityVarStats>();
	private List<SessionSignal> sigStats = new ArrayList<SessionSignal>();
	
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
	public List<SessionEntityVarStats> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<SessionEntityVarStats> varStats) {
		this.varStats = varStats;
	}
	public List<SessionSignal> getSigStats() {
		return sigStats;
	}
	public void setSigStats(List<SessionSignal> sigStats) {
		this.sigStats = sigStats;
	}
	
	
	
}

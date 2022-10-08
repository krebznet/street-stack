package com.dunkware.xstream.model.agg;

import java.time.LocalDate;
import java.util.List;

public class EntityDayAgg {
	
	private LocalDate date;
	private String ident; 
	private long id; 
	private List<EntityVarAgg> vars;
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<EntityVarAgg> getVars() {
		return vars;
	}
	public void setVars(List<EntityVarAgg> vars) {
		this.vars = vars;
	}
	
	

}

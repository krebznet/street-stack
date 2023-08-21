package com.dunkware.xstream.model.stats;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EntityStatsSession {
	
	private LocalDate date;
	private int id; 
	private String ident;  
	private String stream;
	private int sessionId;
	
	private List<EntityStatsSessionVar> vars = new ArrayList<EntityStatsSessionVar>();
	
	@JsonIgnore
	public static ConcurrentHashMap<String,EntityStatsSessionVar> varMap = new ConcurrentHashMap<String, EntityStatsSessionVar>();
	
	
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
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
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public List<EntityStatsSessionVar> getVars() {
		return vars;
	}
	public void setVars(List<EntityStatsSessionVar> vars) {
		this.vars = vars;
		for (EntityStatsSessionVar entityStatsSessionVar : vars) {
			varMap.put(entityStatsSessionVar.getIdent(), entityStatsSessionVar);
		}
	} 
	
	@JsonIgnore
	public EntityStatsSessionVar getVar(String ident) { 
		if(varMap.size() == 0 && vars.size() > 0) { 
			for (EntityStatsSessionVar entityStatsSessionVar : vars) {
				varMap.put(entityStatsSessionVar.getIdent(), entityStatsSessionVar);
			}	
		}
		return varMap.get(ident);
	}
	
	
	
	
	
	

}

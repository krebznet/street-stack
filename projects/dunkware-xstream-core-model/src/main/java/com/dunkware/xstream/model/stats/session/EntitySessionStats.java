package com.dunkware.xstream.model.stats.session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntitySessionStats {
	
	private LocalDate date;
	private int id; 
	private String ident;  
	private String stream;
	private int sessionId;
	
	private List<EntityStat> stats = new ArrayList<EntityStat>();
	

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
	
	
	
	
	
	
	
	
	
	

}

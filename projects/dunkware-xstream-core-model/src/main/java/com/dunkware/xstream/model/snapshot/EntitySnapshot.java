package com.dunkware.xstream.model.snapshot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntitySnapshot {
	
	private int id; 
	private String ident; 
	private List<EntitySnapshotVar> vars = new ArrayList<EntitySnapshotVar>();
	private LocalTime time;
	private String timeString; 
	private String error = null;
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public List<EntitySnapshotVar> getVars() {
		return vars;
	}
	public void setVars(List<EntitySnapshotVar> vars) {
		this.vars = vars;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	} 
	
	
	
	
	
	
	
	

}

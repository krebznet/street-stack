package com.dunkware.xstream.model.snapshot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntitySnapshot {
	
	private String id; 
	private String ident; 
	private List<EntitySnapshotVar> vars = new ArrayList<EntitySnapshotVar>();
	private LocalTime time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	
	

}

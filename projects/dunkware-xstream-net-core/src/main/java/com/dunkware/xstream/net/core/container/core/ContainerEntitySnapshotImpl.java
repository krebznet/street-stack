package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;

import com.dunkware.xstream.net.core.container.ContainerEntitySnapshot;
import com.dunkware.xstream.net.core.container.ContainerValueSet;

public class ContainerEntitySnapshotImpl implements ContainerEntitySnapshot {
	
	private LocalDateTime time; 
	private ContainerValueSet vars; 
	private String ident; 
	private int id; 
	
	public ContainerEntitySnapshotImpl(LocalDateTime time, ContainerValueSet set, String ident, int id) { 
		this.vars = set;
		this.time = time;
	}

	@Override
	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public ContainerValueSet getVars() {
		return vars;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public void setVars(ContainerValueSet vars) {
		this.vars = vars;
	}

	
	
	
	

}

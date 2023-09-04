package com.dunkware.trade.service.stream.descriptor;

import java.time.LocalDate;

public class StreamEntityDescriptor {
	
	private int id; 
	private String ident; 
	private String name; 
	private LocalDate since;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getSince() {
		return since;
	}
	public void setSince(LocalDate since) {
		this.since = since;
	} 
	
	

}

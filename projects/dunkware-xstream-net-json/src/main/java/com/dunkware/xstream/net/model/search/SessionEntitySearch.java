package com.dunkware.xstream.net.model.search;

public class SessionEntitySearch {

	private SessionEntitySearchType type; 
	private SessionEntitySearchFilter fiter;
	private String name;
	
	public SessionEntitySearchType getType() {
		return type;
	}
	public void setType(SessionEntitySearchType type) {
		this.type = type;
	}
	public SessionEntitySearchFilter getFiter() {
		return fiter;
	}
	public void setFiter(SessionEntitySearchFilter fiter) {
		this.fiter = fiter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
}

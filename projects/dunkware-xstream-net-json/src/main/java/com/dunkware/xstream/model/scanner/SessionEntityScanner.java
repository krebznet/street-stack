package com.dunkware.xstream.model.scanner;

import java.util.List;

import com.dunkware.xstream.model.search.SessionEntitySearch;

public class SessionEntityScanner {
	
	private SessionEntitySearch search; 
	private List<String> vars;
	private String streamIdentifier;
	
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	}
	public List<String> getVars() {
		return vars;
	}
	public void setVars(List<String> vars) {
		this.vars = vars;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	
	
	
	
	

}

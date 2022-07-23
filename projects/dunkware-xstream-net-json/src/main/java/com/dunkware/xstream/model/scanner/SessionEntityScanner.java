package com.dunkware.xstream.model.scanner;

import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionEntityScanner {
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private SessionEntitySearch search; 
	private String name; 
	private String streamIdentifier;
	private Number id; 
	
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	}
	
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	
	

	
	
	
	
	
	
	
	
	

}

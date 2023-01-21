package com.dunkware.xstream.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionEntitySearch {

	private SessionEntitySearchType type; 
	private SessionEntitySearchFilter filterSearch;
	private String streamIdentifier; 
	private String name;
	
	public SessionEntitySearchType getType() {
		return type;
	}
	public void setType(SessionEntitySearchType type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public SessionEntitySearchFilter getFilterSearch() {
		return filterSearch;
	}
	
	public void setFilterSearch(SessionEntitySearchFilter filterSearch) {
		this.filterSearch = filterSearch;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.dunkware.xstream.net.proto;

import java.util.List;

import com.dunkware.xstream.model.search.SessionEntitySearch;

public class StreamContainerEntitySearchReq {

	private String identifier; 
	private SessionEntitySearch search; 
	private List<String> fields;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	
	
}

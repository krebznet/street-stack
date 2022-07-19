package com.dunkware.xstream.model.search;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionEntitySearch {

	private SessionEntitySearchType type; 
	private SessionEntitySearchFilter filterSearch;
	private List<String> retVars = new ArrayList<String>();
	
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
	
	public List<String> getRetVars() {
		return retVars;
	}
	
	public void setRetVars(List<String> retVars) {
		this.retVars = retVars;
	}
	
	
	
	
	
	
	
	
	
	
	
}

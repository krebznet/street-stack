package com.dunkware.xstream.model.search;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionEntitySearchFilter {

	private List<SessionEntityFilter> filters = new ArrayList<SessionEntityFilter>();

	public List<SessionEntityFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<SessionEntityFilter> filters) {
		this.filters = filters;
	}
	
	
}

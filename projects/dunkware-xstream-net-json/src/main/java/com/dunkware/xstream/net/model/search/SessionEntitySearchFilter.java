package com.dunkware.xstream.net.model.search;

import java.util.ArrayList;
import java.util.List;

public class SessionEntitySearchFilter {

	private List<SessionEntityFilter> filters = new ArrayList<SessionEntityFilter>();

	public List<SessionEntityFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<SessionEntityFilter> filters) {
		this.filters = filters;
	}
	
	
}

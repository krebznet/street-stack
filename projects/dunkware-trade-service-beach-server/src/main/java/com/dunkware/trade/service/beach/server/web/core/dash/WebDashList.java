package com.dunkware.trade.service.beach.server.web.core.dash;

public interface WebDashList {
	
	void addFilter(WebDashScope filter);
	
	void removeFilters();
	
	void removeFilter(WebDashScope filter);

}

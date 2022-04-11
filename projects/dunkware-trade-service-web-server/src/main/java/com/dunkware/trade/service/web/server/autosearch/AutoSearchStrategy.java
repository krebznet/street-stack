package com.dunkware.trade.service.web.server.autosearch;

public interface AutoSearchStrategy {
	
	public void init(AutoSearchContext context);
	
	public String handleSearch(String request);
	
	public void dispose(); 

}

package com.dunkware.trade.service.web.server.autosearch.strategies;

import com.dunkware.trade.service.web.server.autosearch.AutoSearchContext;
import com.dunkware.trade.service.web.server.autosearch.AutoSearchStrategy;

/** 
 * Simple Search Strategy that echos request back 
 * @author duncankrebs
 *
 */
public class AutoSearchEchoStrategy implements AutoSearchStrategy {

	private AutoSearchContext context;
	
	@Override
	public void init(AutoSearchContext context) {
		this.context = context; 
	}
	
	@Override
	public String handleSearch(String request) {
		return request;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

	
	
	

	
}

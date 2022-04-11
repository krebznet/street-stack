package com.dunkware.genesis.service.autosearch.strategies;

import com.dunkware.genesis.service.autosearch.AutoSearchStrategy;
import com.dunkware.genesis.service.autosearch.AutoSearchContext;

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

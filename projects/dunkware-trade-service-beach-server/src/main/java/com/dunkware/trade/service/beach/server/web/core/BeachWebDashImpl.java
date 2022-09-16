package com.dunkware.trade.service.beach.server.web.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.trade.service.beach.server.trade.BeachService;
import com.dunkware.trade.service.beach.server.web.BeachWebDash;

import comm.dunkware.trade.service.beach.web.model.WebScope;
import comm.dunkware.trade.service.beach.web.model.WebScopeLevel;

public class BeachWebDashImpl implements BeachWebDash {

	@Autowired
	private BeachService beachService; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	private WebScopeLevel scopeLevel = null;

	
	@Override
	public void scope(WebScope scope) throws Exception {
		if(scopeLevel == null) { 
			
		}
	}

	@Override
	public void scopeReset() {
		// TODO Auto-generated method stub
		
	}

	public BeachService getBeachService() {
		return beachService;
	}

	public void setBeachService(BeachService beachService) {
		this.beachService = beachService;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public WebScopeLevel getScopeLevel() {
		return scopeLevel;
	}

	public void setScopeLevel(WebScopeLevel scopeLevel) {
		this.scopeLevel = scopeLevel;
	}

	@Override
	public NetScanner getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NetScanner getTrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NetScanner getAccounts() {
		// TODO Auto-generated method stub
		// BeachAccountSpec -> and turn it to a list 
		return null;
	}

	@Override
	public NetScanner getEvents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	

}

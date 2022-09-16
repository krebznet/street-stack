package com.dunkware.trade.service.beach.server.web;

import com.dunkware.common.util.data.NetScanner;

import comm.dunkware.trade.service.beach.web.model.WebScope;

public interface BeachWebDash {

	void scope(WebScope scope) throws Exception;
	
	
	void scopeReset();
	
	/**
	 * Returns the dynamic order list
	 * @return
	 */
	NetScanner getOrders();
	
	/**
	 * Returns the trades
	 * @return
	 */
	NetScanner getTrades();
	
	
	NetScanner getAccounts();
	
	NetScanner getEvents();
	
	// getDash
}

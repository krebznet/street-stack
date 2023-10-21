package com.dunkware.trade.service.beach.server.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class BeachTradeCloser {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private BeachRepo repo;
	
	/**
	 * Started not restored from existing JVM instance
	 * @param trade
	 */
	public void start(BeachTrade trade) { 
		//
		// set status -> // not sure - how to do that
		// submit the order 
		
		
	}
	
	

}

package com.dunkware.trade.service.beach.server.trade;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.trade.exittrigger.BeachExitTrigger;

public class BeachExitManager {

	private Logger loggger = LoggerFactory.getLogger(getClass());
	
	private ConcurrentHashMap<Long, BeachExitTrigger> exitTriggers = new ConcurrentHashMap<Long, BeachExitTrigger>();
	
	
	/**
	 * Starts the exit manager does not resume it from previous JVM instance
	 * @param trade
	 */
	public void start(BeachTrade trade) { 
		// create exit triggers 
		// create exit trigger entitties 
		// assign enttiy id on each model
		// start the exit triggers. 
	}
	
	
	/**
	 * This will force the trade into a closing status.
	 * cancel the stop loss and submit a new order. 
	 */
	public void closeOverride() { 
		
	}
	
	
	/**
	 * This will force the trade into a closing status by 
	 * by an exit trigger. 
	 * @param trigger
	 */
	public void triggerExit(BeachExitTrigger trigger) { 
		
	}
}

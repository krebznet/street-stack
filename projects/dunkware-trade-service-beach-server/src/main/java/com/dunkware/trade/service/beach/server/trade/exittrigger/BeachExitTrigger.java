package com.dunkware.trade.service.beach.server.trade.exittrigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.beach.model.trade.BeachExitTriggerModel;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public abstract class BeachExitTrigger {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BeachExitTriggerModel model;
	private BeachTrade trade; 
	
	public void start(BeachExitTriggerModel model, BeachTrade trade) { 
		this.model = model;
		this.trade = trade; 
	}
	
	protected abstract void doStart();
	
	/**
	 * Make an async call to persist the trigger state. 
	 */
	public void persist() { 
		
	}
	
	public BeachTrade getTrade() { 
		return trade; 
	}
	
	public BeachExitTriggerModel getModel() { 
		return this.model;
	}
	
	public abstract Object getState();
	
	

}

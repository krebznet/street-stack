package com.dunkware.trade.service.beach.server.runtime;

import org.slf4j.Logger;

import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.server.entity.BeachSystemEntity;

public class BeachSystem {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	
	private BeachAccount account; 
	
	private BeachSystemEntity entity; 
	
	
	public void init(BeachSystemEntity ent, BeachAccount account) { 
		this.account = account;
		this.entity = ent; 
	}
	
	
	public String submitTrade(BeachTradeModel model) throws Exception { 
		return "YEAH"; 
		// 
	}
	
	public String getName() { 
		return entity.getName();
	}
}

package com.dunkware.trade.service.beach.server.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachOrder;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.BeachTradeBean;

public class BeachTradeOpener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DEventNode eventNode; 
	
	private BeachTrade trade;
	
	@Autowired
	private BeachRepo repo;
	
	@Autowired
	private ApplicationContext ac;
	
	private BeachTradeBean bean;
	
	private BeachOrder beachOrder; // ? 
	
	private BeachTradeModel model; 
	
	/**
	 * Starts the trade opener, different than resuming
	 * from existing JVM instance? 
	 * @param trade
	 */
	public void start(BeachTrade trade) { 
		this.trade = trade; 
		this.model = trade.getModel();
		this.eventNode = trade.getEventNode().createChild(this);
		// so the first thing is create this order you are talking about
		// going to need a test API during market hours first. 
		
		// EBeachTradeOpen event 
		// EBeachTradeOpenException 
	}
	

	
	
	

}

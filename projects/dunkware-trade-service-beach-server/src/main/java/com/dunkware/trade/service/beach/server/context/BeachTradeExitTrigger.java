package com.dunkware.trade.service.beach.server.context;

import com.dunkware.trade.service.beach.protocol.play.PlayExitTrigger;

public interface BeachTradeExitTrigger {

	public void init(PlayExitTrigger trigger, BeachTradeExitTriggers exit) throws Exception; 
	
	public void start(); 
	
	public void stop() throws Exception; 
	
	
}

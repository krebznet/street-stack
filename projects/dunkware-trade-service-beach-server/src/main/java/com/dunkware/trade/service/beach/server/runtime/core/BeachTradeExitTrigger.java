package com.dunkware.trade.service.beach.server.runtime.core;

import com.dunkware.trade.service.beach.protocol.play.PlayExitTrigger;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public interface BeachTradeExitTrigger {

	void init(BeachTrade trade, PlayExitTrigger model) throws Exception;
	
	void start(); 
	
	void dispose();
}

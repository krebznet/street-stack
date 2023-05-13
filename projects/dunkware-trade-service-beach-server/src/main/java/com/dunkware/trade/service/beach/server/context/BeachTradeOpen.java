package com.dunkware.trade.service.beach.server.context;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public interface BeachTradeOpen {

	DEventNode getEventNode(); 
	
	void start(BeachTrade trade); 
	

}

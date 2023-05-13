package com.dunkware.trade.service.beach.server.context;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public interface BeachTradeClose {

	public void start(BeachTrade trade); 
	
	
	public DEventNode getEventNode();
}

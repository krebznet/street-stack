package com.dunkware.trade.service.beach.server.runtime.core;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public interface BeachTradeEntry {

	DEventNode getEventNode(); 
	
	void start(BeachTrade trade); 
	
	double getCommission();
	
	int getFilledSize();

}

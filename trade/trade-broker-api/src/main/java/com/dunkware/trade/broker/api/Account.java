package com.dunkware.trade.broker.api;

import com.dunkware.utils.core.events.DunkEventNode;

public interface Account extends OrderExecutor {
	
	
	
	String getIdentifier();
	
	DunkEventNode getEventNode();
	
	Broker<?> getBroker();
	
	AccountBean getBean();
	
	AccountStatus getStatus();
	
	
	void init();
	
	void dispose();

}

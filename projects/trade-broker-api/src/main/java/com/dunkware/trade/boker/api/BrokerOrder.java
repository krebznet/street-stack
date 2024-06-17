package com.dunkware.trade.boker.api;

import com.dunkware.utils.core.events.DunkEventNode;

public interface BrokerOrder {

	BrokerOrderStatus getStatus();
		
	public void send();
	
	public void cancel() ;

	public BrokerOrderBean getBean();

	DunkEventNode getEventNode();
}

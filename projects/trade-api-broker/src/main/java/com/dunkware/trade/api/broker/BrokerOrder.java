package com.dunkware.trade.api.broker;

import com.dunkware.utils.core.events.DunkEventNode;

public interface BrokerOrder {

	BrokerOrderStatus getStatus();
		
	public void send();
	
	public void cancel() ;

	public BrokerOrderBean getBean();

	DunkEventNode getEventNode();
}

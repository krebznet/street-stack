package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;
import com.dunkware.utils.core.events.DunkEvent;

public class EBrokerOrderEvent extends DunkEvent  {

	private BrokerOrder order; 
	
	public EBrokerOrderEvent(BrokerOrder order) { 
		this.order = order;
	}
	
	public BrokerOrder getOrder() { 
		return order;
	}
}

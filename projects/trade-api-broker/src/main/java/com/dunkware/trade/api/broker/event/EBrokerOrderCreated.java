package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderCreated extends EBrokerOrderUpdate  {

	public EBrokerOrderCreated(BrokerOrder order) {
		super(order);
	}

}

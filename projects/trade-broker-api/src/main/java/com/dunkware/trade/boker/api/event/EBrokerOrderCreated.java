package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderCreated extends EBrokerOrderUpdate  {

	public EBrokerOrderCreated(BrokerOrder order) {
		super(order);
	}

}

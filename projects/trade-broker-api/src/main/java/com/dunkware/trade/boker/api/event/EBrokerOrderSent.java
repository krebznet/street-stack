package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderSent extends EBrokerOrderUpdate  {

	public EBrokerOrderSent(BrokerOrder order) {
		super(order);
	}

}

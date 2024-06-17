package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderSent extends EBrokerOrderUpdate  {

	public EBrokerOrderSent(BrokerOrder order) {
		super(order);
	}

}

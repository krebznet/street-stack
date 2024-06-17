package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderRejected extends EBrokerOrderUpdate  {

	public EBrokerOrderRejected(BrokerOrder order) {
		super(order);
	}

}

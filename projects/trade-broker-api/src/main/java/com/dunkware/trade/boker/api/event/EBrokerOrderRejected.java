package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderRejected extends EBrokerOrderUpdate  {

	public EBrokerOrderRejected(BrokerOrder order) {
		super(order);
	}

}

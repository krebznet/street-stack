package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderCancelled extends EBrokerOrderUpdate  {

	public EBrokerOrderCancelled(BrokerOrder order) {
		super(order);
	}

}

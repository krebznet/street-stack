package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderFilled extends EBrokerOrderUpdate  {

	public EBrokerOrderFilled(BrokerOrder order) {
		super(order);
	}

}

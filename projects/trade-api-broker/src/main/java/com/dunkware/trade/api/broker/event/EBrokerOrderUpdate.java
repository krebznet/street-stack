package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderUpdate extends EBrokerOrderEvent  {

	public EBrokerOrderUpdate(BrokerOrder order) {
		super(order);
	}

}

package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderSubmitted extends EBrokerOrderUpdate  {

	public EBrokerOrderSubmitted(BrokerOrder order) {
		super(order);
	}

}

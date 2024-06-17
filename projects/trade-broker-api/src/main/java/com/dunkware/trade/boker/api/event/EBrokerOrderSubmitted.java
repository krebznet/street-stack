package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderSubmitted extends EBrokerOrderUpdate  {

	public EBrokerOrderSubmitted(BrokerOrder order) {
		super(order);
	}

}

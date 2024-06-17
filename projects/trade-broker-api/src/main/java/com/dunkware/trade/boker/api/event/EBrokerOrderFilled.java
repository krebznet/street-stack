package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderFilled extends EBrokerOrderUpdate  {

	public EBrokerOrderFilled(BrokerOrder order) {
		super(order);
	}

}

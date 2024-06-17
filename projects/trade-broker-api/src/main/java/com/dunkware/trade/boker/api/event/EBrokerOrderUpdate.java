package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderUpdate extends EBrokerOrderEvent  {

	public EBrokerOrderUpdate(BrokerOrder order) {
		super(order);
	}

}

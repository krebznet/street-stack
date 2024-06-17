package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderCancelled extends EBrokerOrderUpdate  {

	public EBrokerOrderCancelled(BrokerOrder order) {
		super(order);
	}

}

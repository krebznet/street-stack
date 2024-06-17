package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderStatusUpdate extends EBrokerOrderUpdate  {

	public EBrokerOrderStatusUpdate(BrokerOrder order) {
		super(order);
	}

}

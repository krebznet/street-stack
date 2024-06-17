package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderStatusUpdate extends EBrokerOrderUpdate  {

	public EBrokerOrderStatusUpdate(BrokerOrder order) {
		super(order);
	}

}

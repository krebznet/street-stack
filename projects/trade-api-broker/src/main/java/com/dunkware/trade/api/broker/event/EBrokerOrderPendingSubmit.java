package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderPendingSubmit extends EBrokerOrderUpdate  {

	public EBrokerOrderPendingSubmit(BrokerOrder order) {
		super(order);
	}

}

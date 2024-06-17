package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderPendingSubmit extends EBrokerOrderUpdate  {

	public EBrokerOrderPendingSubmit(BrokerOrder order) {
		super(order);
	}

}

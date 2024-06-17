package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderPreSubmitted extends EBrokerOrderUpdate  {

	public EBrokerOrderPreSubmitted(BrokerOrder order) {
		super(order);
	}

}

package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderPreSubmitted extends EBrokerOrderUpdate  {

	public EBrokerOrderPreSubmitted(BrokerOrder order) {
		super(order);
	}

}

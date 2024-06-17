package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.BrokerOrder;

public class EBrokerOrderException extends EBrokerOrderUpdate  {

	public EBrokerOrderException(BrokerOrder order) {
		super(order);
	}

}

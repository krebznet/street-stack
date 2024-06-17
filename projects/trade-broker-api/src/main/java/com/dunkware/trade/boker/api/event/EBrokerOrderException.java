package com.dunkware.trade.boker.api.event;

import com.dunkware.trade.boker.api.BrokerOrder;

public class EBrokerOrderException extends EBrokerOrderUpdate  {

	public EBrokerOrderException(BrokerOrder order) {
		super(order);
	}

}

package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerConnected extends EBrokerEvent  {

	public EBrokerConnected(Broker broker) {
		super(broker);
	}

}

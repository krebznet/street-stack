package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerConnecting extends EBrokerEvent  {

	public EBrokerConnecting(Broker broker) {
		super(broker);
	}

}

package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerInitialized extends EBrokerEvent {

	public EBrokerInitialized(Broker broker) {
		super(broker);
	}

}

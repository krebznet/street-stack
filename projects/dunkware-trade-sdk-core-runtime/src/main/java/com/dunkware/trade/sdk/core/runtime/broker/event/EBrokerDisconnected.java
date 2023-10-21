package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerDisconnected extends EBrokerEvent {

	public EBrokerDisconnected(Broker broker) {
		super(broker);
	}

}

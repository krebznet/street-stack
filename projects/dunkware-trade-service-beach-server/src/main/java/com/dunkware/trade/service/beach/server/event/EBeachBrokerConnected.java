package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.broker.BeachBroker;

public class EBeachBrokerConnected extends EBeachBrokerEvent {

	public EBeachBrokerConnected(BeachBroker broker) {
		super(broker);
	}

}

package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachBroker;

public class EBeachBrokerInitialized extends EBeachBrokerEvent {

	public EBeachBrokerInitialized(BeachBroker broker) {
		super(broker);
	}

}

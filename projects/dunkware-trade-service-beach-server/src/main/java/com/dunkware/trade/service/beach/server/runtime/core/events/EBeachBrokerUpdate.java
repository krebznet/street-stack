package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachBroker;

public class EBeachBrokerUpdate extends DEvent {

	private BeachBroker broker;
	
	public EBeachBrokerUpdate(BeachBroker broker) { 
		this.broker = broker;
	}
	
	public BeachBroker getBroker() { 
		return broker;
	}
}

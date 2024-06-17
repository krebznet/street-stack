package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;
import com.dunkware.utils.core.events.DunkEvent;

public class EStreetBrokerEvent extends DunkEvent {
	
	private StreetBroker broker; 
	
	public EStreetBrokerEvent(StreetBroker broker) { 
		this.broker = broker;
	}
	
	public StreetBroker getBroker() { 
		return broker; 
	}
	
	
	

}

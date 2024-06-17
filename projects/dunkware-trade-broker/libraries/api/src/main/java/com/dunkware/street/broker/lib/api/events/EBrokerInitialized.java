package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;

public class EBrokerInitialized extends EStreetBrokerEvent {

	public EBrokerInitialized(StreetBroker broker) {
		super(broker);
	}

}

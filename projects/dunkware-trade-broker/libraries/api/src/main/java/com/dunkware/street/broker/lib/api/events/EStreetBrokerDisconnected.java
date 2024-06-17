package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;

public class EStreetBrokerDisconnected extends EStreetBrokerEvent {

	public EStreetBrokerDisconnected(StreetBroker broker) {
		super(broker);
	}

}

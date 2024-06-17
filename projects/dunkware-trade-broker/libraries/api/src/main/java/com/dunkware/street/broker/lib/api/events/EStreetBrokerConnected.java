package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;

public class EStreetBrokerConnected extends EStreetBrokerEvent  {

	public EStreetBrokerConnected(StreetBroker broker) {
		super(broker);
	}

}

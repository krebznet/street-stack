package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;

public class EBrokerConnecting extends EStreetBrokerEvent  {

	public EBrokerConnecting(StreetBroker broker) {
		super(broker);
	}

}

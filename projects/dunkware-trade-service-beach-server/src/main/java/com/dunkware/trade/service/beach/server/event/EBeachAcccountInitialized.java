package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccount;

public class EBeachAcccountInitialized extends EBeachAccountEvent {

	public EBeachAcccountInitialized(BeachBrokerAccount account) {
		super(account);
	}

}

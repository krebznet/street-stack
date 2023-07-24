package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachAccount;

public class EBeachAcccountInitialized extends EBeachAccountEvent {

	public EBeachAcccountInitialized(BeachAccount account) {
		super(account);
	}

}

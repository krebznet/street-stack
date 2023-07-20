package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachAccount;

public class EBeachAcccountInitialized extends EBeachAccountUpdate {

	public EBeachAcccountInitialized(BeachAccount account) {
		super(account);
	}

}

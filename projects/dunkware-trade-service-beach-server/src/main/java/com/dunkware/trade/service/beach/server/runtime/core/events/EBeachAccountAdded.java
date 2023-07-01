package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachAccount;

public class EBeachAccountAdded extends EBeachAccountUpdate {

	public EBeachAccountAdded(BeachAccount account) {
		super(account);
	}

}

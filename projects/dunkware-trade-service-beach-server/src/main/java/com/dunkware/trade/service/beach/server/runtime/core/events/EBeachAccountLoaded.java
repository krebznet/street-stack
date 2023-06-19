package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachAccount;

public class EBeachAccountLoaded extends EBeachAccountUpdate {

	public EBeachAccountLoaded(BeachAccount account) {
		super(account);
	}

}

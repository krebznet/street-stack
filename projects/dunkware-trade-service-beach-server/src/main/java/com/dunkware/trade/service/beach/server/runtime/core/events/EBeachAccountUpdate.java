package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;

public class EBeachAccountUpdate extends DEvent {
	
	private BeachAccount account;

	public EBeachAccountUpdate(BeachAccount account) { 
		this.account = account; 
	}
	
	public BeachAccount getAcccount() { 
		return account;
	}
}

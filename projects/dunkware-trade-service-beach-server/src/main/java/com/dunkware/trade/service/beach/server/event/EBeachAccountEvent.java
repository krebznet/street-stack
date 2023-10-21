package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;

public class EBeachAccountEvent extends DEvent {
	
	private BeachAccount account;

	public EBeachAccountEvent(BeachAccount account) { 
		this.account = account; 
	}
	
	public BeachAccount getAcccount() { 
		return account;
	}
}

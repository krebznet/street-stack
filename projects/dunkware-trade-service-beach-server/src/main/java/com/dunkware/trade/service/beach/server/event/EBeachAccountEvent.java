package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccount;

public class EBeachAccountEvent extends DEvent {
	
	private BeachBrokerAccount account;

	public EBeachAccountEvent(BeachBrokerAccount account) { 
		this.account = account; 
	}
	
	public BeachBrokerAccount getAcccount() { 
		return account;
	}
}

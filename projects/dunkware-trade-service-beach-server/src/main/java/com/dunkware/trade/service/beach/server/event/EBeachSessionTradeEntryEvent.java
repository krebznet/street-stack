package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.session.BeachSessionTradeEntry;

public class EBeachSessionTradeEntryEvent extends DEvent {

	private BeachSessionTradeEntry entry; 
	
	public EBeachSessionTradeEntryEvent(BeachSessionTradeEntry entry) { 
		this.entry = entry; 
	}
	
	public BeachSessionTradeEntry getEntry() { 
		return entry;
	}
}

package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeEntry;

public class EBeachTradeEntryComplete extends EBeachTradeEvent {

	private BeachTradeEntry entry; 
	
	public EBeachTradeEntryComplete(BeachTrade trade, BeachTradeEntry entry) {
		super(trade);
	}
	
	public BeachTradeEntry getEntry() { 
		return entry;
	}
	

}

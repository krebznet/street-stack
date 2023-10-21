package com.dunkware.trade.sdk.core.runtime.trade.event;

import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;

public class ETradeEntry extends ETrade  {

	private TradeEntry entry; 
	
	public ETradeEntry(TradeEntry entry) {
		super(entry.getTrade());
		this.entry = entry;
	}
	
	public TradeEntry getEntry() { 
		return entry;
	}

}

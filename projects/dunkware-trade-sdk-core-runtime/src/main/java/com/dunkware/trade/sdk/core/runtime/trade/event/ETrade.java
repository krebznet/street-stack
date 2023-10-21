package com.dunkware.trade.sdk.core.runtime.trade.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;

public class ETrade extends DEvent {

	private Trade trade; 
	
	public ETrade(Trade trade) { 
		this.trade = trade;
	}
	
	public Trade getTrade() { 
		return trade;
	}
}

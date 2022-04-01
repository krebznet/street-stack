package com.dunkware.trade.sdk.core.runtime.trade.event;

import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;

public class ETradeExit extends ETrade {

	private TradeExit exit; 
	
	public ETradeExit(TradeExit exit) {
		super(exit.getTrade());
		this.exit = exit; 
	}
	
	public TradeExit getExit() { 
		return exit;
	}

	
}

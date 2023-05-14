package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExit;

public class EBeachTradeExitComplete extends EBeachTradeEvent {

	private BeachTradeExit exit; 
	
	public EBeachTradeExitComplete(BeachTrade trade, BeachTradeExit exit) {
		super(trade);
		this.exit = exit;
	}
	
	public BeachTradeExit getExit() {
		return exit;
	}
	

}

package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExit;

public class EBeachTradeExitException extends EBeachTradeEvent {

	private BeachTradeExit exit; 
	private String error;
	
	public EBeachTradeExitException(BeachTrade trade, BeachTradeExit exit, String error) {
		super(trade);
		this.error = error;
		this.exit = exit;
	}
	
	public String getError() { 
		return error;
	}
	public BeachTradeExit getExit() {
		return exit;
	}
	

}

package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class EBeachTradeEntryException extends DEvent {
	
	private String exception; 
	private BeachTrade trade;
	
	public EBeachTradeEntryException(BeachTrade trade, String exception) { 
		this.exception = exception;
		this.trade = trade;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public BeachTrade getTrade() {
		return trade;
	}

	public void setTrade(BeachTrade trade) {
		this.trade = trade;
	}
	
	

}

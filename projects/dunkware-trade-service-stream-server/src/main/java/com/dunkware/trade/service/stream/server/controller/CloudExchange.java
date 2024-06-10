package com.dunkware.trade.service.stream.server.controller;

import java.util.List;

import com.dunkware.trade.service.stream.server.facade.model.ExchangeSession;
import com.dunkware.trade.service.stream.server.facade.model.TickerRef;

public interface CloudExchange {
	
	
	List<TickerRef> getExchangeTickers();
	
	int getExchangeId();
	
	List<ExchangeSession> getExchangeSessions();
	
	

}

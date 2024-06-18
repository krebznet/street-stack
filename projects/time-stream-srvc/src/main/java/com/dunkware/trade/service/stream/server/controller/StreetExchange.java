package com.dunkware.trade.service.stream.server.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.dunkware.trade.service.stream.server.facade.model.ExchangeSession;
import com.dunkware.trade.service.stream.server.facade.model.TickerRef;

public interface StreetExchange {
	
	
	List<TickerRef> getExchangeTickers();
	
	int getExchangeId();
	
	List<ExchangeSession> getExchangeSessions();
	
	ZoneId getTimeZone();
	
	LocalDateTime getDateTime();
	
	// StreetStream
		// StreetEntity 
		// 	- getVariableValues(); 
	

}

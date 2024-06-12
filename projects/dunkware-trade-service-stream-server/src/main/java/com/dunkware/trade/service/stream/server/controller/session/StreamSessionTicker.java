package com.dunkware.trade.service.stream.server.controller.session;

import java.time.LocalDateTime;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface StreamSessionTicker {

	public TradeTickerSpec getSpec();
	
	StreamSessionNode getNode();
	
	LocalDateTime getAdLocalTime();
}

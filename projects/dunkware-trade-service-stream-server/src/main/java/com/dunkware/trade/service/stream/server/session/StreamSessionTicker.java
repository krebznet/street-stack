package com.dunkware.trade.service.stream.server.session;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface StreamSessionTicker {

	public TradeTickerSpec getSpec();
	
	StreamSessionNode getNode();
	
	DDateTime getAddTime();
}

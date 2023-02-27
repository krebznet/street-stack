package com.dunkware.trade.sdk.core.runtime.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface TradeContext {
	
	DEventNode getEventNode();
	
	TradeList getTrades();

	void execute(Runnable runnable);
		
	Instrument getInstrument(TradeTickerSpec tickerSpec) throws Exception;
	
}

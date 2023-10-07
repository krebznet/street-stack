package com.dunkware.trade.tick.api.instrument;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface InstrumentProvider {
	
	public Instrument acquireInstrument(TradeTickerSpec ticker) throws Exception;
	
	public void releaseInstrument(Instrument instrument);

}

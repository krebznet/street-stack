package com.dunkware.trade.sdk.core.runtime.play;

import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;

public interface Play {
	
	String getName();
	
	TradeEntry getEntry();
	
	TradeExit getExit();

}

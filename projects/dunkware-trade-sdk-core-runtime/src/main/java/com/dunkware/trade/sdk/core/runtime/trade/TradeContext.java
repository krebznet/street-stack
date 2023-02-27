package com.dunkware.trade.sdk.core.runtime.trade;

import java.util.Collection;
import java.util.stream.Stream;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.TradeType;

public interface TradeContext {
	
	DEventNode getEventNode();
	
	Collection<Trade> getTrades(); 
	
	Trade createTrade(TradeType type) throws Exception;

	void execute(Runnable runnable);
	
	Stream getStream(String ident) throws Exception;
	
	void event(String source, String type, String message) throws Exception;
	
	
}

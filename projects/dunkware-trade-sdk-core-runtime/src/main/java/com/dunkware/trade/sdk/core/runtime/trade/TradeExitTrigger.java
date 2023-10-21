package com.dunkware.trade.sdk.core.runtime.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.CloseType;

public interface TradeExitTrigger {

	DEventNode getEventNode();
	
	void start(CloseType type) throws Exception;
	
	void cancel() throws Exception; 
	
	
}

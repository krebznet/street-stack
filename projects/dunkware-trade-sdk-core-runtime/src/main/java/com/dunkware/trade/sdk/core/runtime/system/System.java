package com.dunkware.trade.sdk.core.runtime.system;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.trade.TradeContext;

public interface System {

	public void init(SystemType type, TradeContext context) throws SystemException;
	
	public void stop(boolean liquidate); 
	
	
}

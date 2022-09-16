package com.dunkware.trade.sdk.core.runtime.system;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.trade.TradeSession;

public interface System {

	public void init(SystemType type, TradeSession context) throws SystemException;
	
	public void stop(boolean liquidate); 
	
	
}

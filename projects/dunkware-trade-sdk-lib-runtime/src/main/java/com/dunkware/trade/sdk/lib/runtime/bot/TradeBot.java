package com.dunkware.trade.sdk.lib.runtime.bot;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.system.System;
import com.dunkware.trade.sdk.core.runtime.system.SystemException;
import com.dunkware.trade.sdk.core.runtime.trade.TradeSession;
import com.dunkware.trade.sdk.lib.model.bot.TradeBotType;

public class TradeBot implements System {

	private TradeBotType type;

	
	
	@Override
	public void init(SystemType type, TradeSession context) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(boolean liquidate) {
		// TODO Auto-generated method stub
		
	}
	
	

}

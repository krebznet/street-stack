package com.dunkware.trade.sdk.lib.runtime.bot;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.system.System;
import com.dunkware.trade.sdk.core.runtime.system.SystemException;
import com.dunkware.trade.sdk.core.runtime.system.anot.ASystem;
import com.dunkware.trade.sdk.core.runtime.trade.TradeSession;
import com.dunkware.trade.sdk.lib.model.bot.model.TradeBotType;
import com.dunkware.trade.sdk.lib.model.bot.web.WebTradeBot;


@ASystem(type = TradeBotType.class)
public class TradeBot implements System {

	private TradeBot type;

	
	private TradeBotType myType;
	@Override
	public void init(SystemType type, TradeSession context) throws SystemException {
		myType = (TradeBotType)type;
		
		
		
	}

	@Override
	public void stop(boolean liquidate) {
		// TODO Auto-generated method stub
		
	}
	
	

}

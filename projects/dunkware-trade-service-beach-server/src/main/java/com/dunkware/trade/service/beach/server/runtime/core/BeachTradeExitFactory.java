package com.dunkware.trade.service.beach.server.runtime.core;

import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.protocol.play.PlayOrderType;
import com.dunkware.trade.service.beach.server.runtime.core.exit.BeachTradeMarketExit;

public class BeachTradeExitFactory {
	
	public static BeachTradeExit create(Play play) throws Exception {
	
		if (play.getExitType() == PlayOrderType.MARKET) {
			return new BeachTradeMarketExit();
		}
		throw new Exception("PlayOrderType Invalid for BeachTradeExit Factory " + play.getExitType().name());
	}

}

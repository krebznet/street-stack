package com.dunkware.trade.service.beach.server.context;

import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.protocol.play.PlayOrderType;
import com.dunkware.trade.service.beach.server.context.close.BeachTadeLimitChaseExit;
import com.dunkware.trade.service.beach.server.context.close.BeachTradeLimitExit;
import com.dunkware.trade.service.beach.server.context.close.BeachTradeMarketExit;

public class BeachTradeCloseFactory {

	public static BeachTradeClose create(Play play) throws Exception {
		if (play.getExitType() == PlayOrderType.LIMIT) {
			return new BeachTradeLimitExit();
		}
		if (play.getExitType() == PlayOrderType.CHASE) {
			return new BeachTadeLimitChaseExit();
					
		}
		if (play.getExitType() == PlayOrderType.MARKET) {
			return new BeachTradeMarketExit();
		}
		throw new Exception("PlayOrderType Invalid for BeachTradeExit Factory " + play.getExitType().name());
	}
}

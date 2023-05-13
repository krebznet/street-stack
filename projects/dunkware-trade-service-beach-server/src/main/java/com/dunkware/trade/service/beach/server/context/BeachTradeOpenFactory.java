package com.dunkware.trade.service.beach.server.context;

import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.protocol.play.PlayOrderType;
import com.dunkware.trade.service.beach.server.context.open.BeachTradeLimitChaseEntry;
import com.dunkware.trade.service.beach.server.context.open.BeachTradeLimitEntry;
import com.dunkware.trade.service.beach.server.context.open.BeachTradeMarketEntry;

public class BeachTradeOpenFactory {

	public static BeachTradeOpen create(Play play) throws Exception {
		if (play.getEntryType() == PlayOrderType.LIMIT_CHASE) {
			BeachTradeLimitChaseEntry limitChase = new BeachTradeLimitChaseEntry();
			return limitChase;
		}
		if (play.getEntryType() == PlayOrderType.LIMIT) {
			return new BeachTradeLimitEntry();
		}
		if (play.getEntryType() == PlayOrderType.MARKET) {
			return new BeachTradeMarketEntry();
		}
		throw new Exception("Play Entry Type " + play.getEntryType().name() + "not handled in factory");
	}

	

}

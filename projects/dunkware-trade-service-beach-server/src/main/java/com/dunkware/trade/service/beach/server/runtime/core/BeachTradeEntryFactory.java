package com.dunkware.trade.service.beach.server.runtime.core;

import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.protocol.play.PlayOrderType;
import com.dunkware.trade.service.beach.server.runtime.core.entry.BeachTradeMarketEntry;

public class BeachTradeEntryFactory {

	public static BeachTradeEntry create(Play play) throws Exception {
		
		if (play.getEntryType() == PlayOrderType.MARKET) {
			return new BeachTradeMarketEntry();
		}
		throw new Exception("Play Entry Type " + play.getEntryType().name() + "not handled in factory");
	}

	

}

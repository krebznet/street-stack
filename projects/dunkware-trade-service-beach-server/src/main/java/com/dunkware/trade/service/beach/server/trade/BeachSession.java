package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.TradeSession;
import com.dunkware.trade.service.beach.server.trade.entity.BeachSessionDO;

public interface BeachSession extends TradeSession {
	
	BeachSystem getSystem();
	
	/**
	 * Creates a trade in Allocated Status
	 * @param tradeType
	 * @return
	 * @throws Exception
	 */
	BeachTrade createTrade(TradeType tradeType) throws Exception;

	public BeachSessionDO getEntity();
	
	// no spec needed here 
	

}

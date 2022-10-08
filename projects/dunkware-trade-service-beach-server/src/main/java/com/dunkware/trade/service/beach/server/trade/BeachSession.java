package com.dunkware.trade.service.beach.server.trade;

import java.util.Collection;

import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.TradeSession;
import com.dunkware.trade.service.beach.server.system.BeachSystem;
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
	
	/**
	 * Returns all the trades for this session. 
	 * @return
	 */
	Collection<BeachTrade> getTrades();
	
	/**
	 * Returns all the orders for this session. 
	 * @return
	 */
	Collection<BeachOrder> getOrders();

	/**
	 * return the enttiy
	 * @return
	 */
	public BeachSessionDO getEntity();
	
	
	/**
	 * closes the session, not closed until all positions are exited. 
	 */
	public void close() throws Exception;
	
	

	
	
	
	
	// no spec needed here 
	

}

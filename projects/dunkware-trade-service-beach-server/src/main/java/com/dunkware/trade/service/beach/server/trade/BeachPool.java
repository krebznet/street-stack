 package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.TradeContext;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolStatus;
import com.dunkware.trade.service.beach.protocol.trade.pool.spec.BeachPoolSpec;
import com.dunkware.trade.service.beach.server.trade.entity.BeachPoolDO;

public interface BeachPool extends TradeContext {
	
	/**
	 * Creates a trade in Allocated Status
	 * @param tradeType
	 * @return
	 * @throws Exception
	 */
	BeachTrade createTrade(TradeType tradeType) throws Exception;

	/**
	 * Returns the beach broker account that trades are executed through
	 * @return
	 */
	BeachAccount getAccount();
	
	/**
	 * Returns the entity 
	 * @return
	 */
	BeachPoolDO getEntity(); 

	/**
	 * Returns the status 
	 * @return
	 */
	BeachPoolStatus getStatus();
	
	/**
	 * returns the spec
	 * @return
	 */
	BeachPoolSpec getSpec();

}

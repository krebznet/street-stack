package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;

public interface BeachTradeService {
	
	BeachBroker addBroker(BrokerType type) throws Exception; 
	
	BeachBroker[] getBrokers() throws Exception;
	
	boolean brokerExists(String identifier);

	BeachBroker getBroker(String identifier) throws Exception;
	
	BeachAccount getAccount(String broker, String account) throws Exception;

	DEventNode getEventNode();
	
	BeachPool getPool(String identifier) throws Exception;
	
	BeachPool createPool(String broker, String account, String identifier) throws Exception;
	
	boolean poolExists(String identifier);
	
}

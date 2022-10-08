
package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.service.beach.server.system.BeachSystem;

public interface BeachService {
	
	BeachBroker addBroker(BrokerType type) throws Exception; 
	
	BeachBroker[] getBrokers() throws Exception;
	
	boolean brokerExists(String identifier);

	BeachBroker getBroker(String identifier) throws Exception;
	
	BeachAccount getAccount(String broker, String account) throws Exception;

	DEventNode getEventNode();
	
	BeachSystem getSystem(String identifier) throws Exception;
	
	BeachSystem addSystem(String broker, String account, String identifier, SystemType systemType) throws Exception;
	
	boolean systemExists(String identifier);
	
	
	
}

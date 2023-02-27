
package com.dunkware.trade.service.beach.server.runtime;

import java.util.List;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;

public interface BeachService {
	
	BeachBroker addBroker(BrokerType type) throws Exception; 
	
	BeachBroker[] getBrokers() throws Exception;
	
	boolean brokerExists(String identifier);

	BeachBroker getBroker(String identifier) throws Exception;
	
	BeachAccount getAccount(String broker, String account) throws Exception;

	DEventNode getEventNode();
	
	List<BeachAccount> getAccounts();
	
	BeachStream getStream(String identifier) throws Exception;
	
}

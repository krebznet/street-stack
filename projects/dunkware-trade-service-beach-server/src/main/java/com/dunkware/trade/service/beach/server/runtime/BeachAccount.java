package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;

import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.server.repository.BeachAccountDO;

public interface BeachAccount extends BrokerAccount {
	
	String getIdentifier();
	
	BeachAccountDO getEntity();	
	
	Collection<BeachBot> getBots();
	
	BeachBot getBot(String identifier) throws Exception;
	
}

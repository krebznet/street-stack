package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.service.beach.server.repository.BeachBrokerDO;

public interface BeachBroker extends Broker {
	
	Broker getBroker();

	String getIdentifier();
	
	BeachBrokerDO getEntity();
	

}

package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.service.beach.server.trade.entity.BeachBrokerDO;

public interface BeachBroker extends Broker {
	
	Broker getBroker();

	String getIdentifier();
	
	BeachBrokerDO getEntity();
	

}

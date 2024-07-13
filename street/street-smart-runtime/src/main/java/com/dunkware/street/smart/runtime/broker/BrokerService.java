package com.dunkware.street.smart.runtime.broker;

import com.dunkware.street.stream.model.resource.BrokerResourceType;

public interface BrokerService {

	// this can also load brokers as well -- 
	
	public BrokerResource createBroker(BrokerResourceType type);
}

package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.service.beach.server.repository.BeachOrderDO;

public interface BeachOrder extends Order {

	BeachAccount getAccount();
	
	BeachOrderDO getEntity();

}

// what do you need a signal subscriber. 
// SignalListener()

package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.server.trade.entity.BeachAccountDO;

public interface BeachAccount extends BrokerAccount {
	
	String getIdentifier();
	
	BeachAccountDO getEntity();	
	
}

package com.dunkware.trade.broker.api.runtime;

import com.dunkware.trade.broker.api.model.account.AccountBean;
import com.dunkware.trade.broker.api.model.account.AccountStatus;
import com.dunkware.utils.core.event.EventNode;

public interface Account extends OrderExecutor {
	
	String getIdentifier();
	
	EventNode getEventNode();
	
	Broker getBroker();
	
	AccountBean getBean();
	
	AccountStatus getStatus();
	
	
	

}

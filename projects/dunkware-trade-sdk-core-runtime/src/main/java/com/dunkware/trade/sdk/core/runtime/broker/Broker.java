package com.dunkware.trade.sdk.core.runtime.broker;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.sdk.core.model.broker.BrokerBean;
import com.dunkware.trade.sdk.core.model.broker.BrokerSpec;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.tick.api.instrument.InstrumentProvider;

public interface Broker extends InstrumentProvider {

	public void connect(BrokerType type, DEventNode eventNode, DExecutor executor) throws Exception;
	
	public void disconnect() throws Exception;
	
	BrokerStatus getStatus();
	
	public String getException();
	
	BrokerAccount getAccount(String accountId) throws Exception; 
	
	public DEventNode getEventNode();
	
	public BrokerAccount[] getAccounts();
	
	public String getIdentifier();
	
	public DExecutor getExecutor();
	
	public BrokerBean getBrokerBean();
}

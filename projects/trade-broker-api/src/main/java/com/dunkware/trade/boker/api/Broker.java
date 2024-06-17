package com.dunkware.trade.boker.api;

import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;

public interface Broker {

	public void connect(BrokerConfig config, DunkEventNode eventNode, DunkExecutor executor) throws Exception;

	public void disconnect() throws Exception;

	BrokerStatus getStatus();

	public String getException();

	BrokerAccount getAccount(String accountId) throws Exception;

	public DunkEventNode getEventNode();

	public BrokerAccount[] getAccounts();

	public String getIdentifier();

	public DunkExecutor getExecutor();

	public BrokerBean getBrokerBean();
}

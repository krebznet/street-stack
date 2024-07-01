package com.dunkware.trade.broker.api;

import java.util.List;

import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;

public interface Broker {

	public void connect(Object brokerType, DunkEventNode eventNode, DunkExecutor executor);

	public void disconnect() throws Exception;

	BrokerStatus getStatus();

	public String getException();

	Account getAccount(String accountId) throws BrokerException;

	public DunkEventNode getEventNode();

	public List<Account> getAccounts();

	public String getIdentifier();

	public DunkExecutor getExecutor();

	public BrokerBean getBrokerBean();
}


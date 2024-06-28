package com.dunkware.trade.api.broker;

import java.util.List;

import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;

public interface Broker<T> {

	public void connect(T brokerType, DunkEventNode eventNode, DunkExecutor executor);

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


package com.dunkware.trade.broker.api.runtime;

import java.util.List;

import com.dunkware.trade.broker.api.model.broker.BrokerBean;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.event.EventNode;

public interface Broker {

	public void connect(Object brokerType, EventNode eventNode, DunkExecutor executor);

	public void disconnect() throws Exception;

	BrokerStatus getStatus();

	public String getException();

	Account getAccount(String accountId) throws BrokerException;

	public EventNode getEventNode();

	public List<Account> getAccounts();

	public String getIdentifier();

	public DunkExecutor getExecutor();

	public BrokerBean getBrokerBean();
}


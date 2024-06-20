package com.dunkware.trade.api.broker;

import java.util.List;

import com.dunkware.trade.api.broker.model.BrokerBean;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;

public interface Broker {

	public void connect(BrokerProperties config, DunkEventNode eventNode, DunkExecutor executor) throws Exception;

	public void disconnect() throws Exception;

	BrokerStatus getStatus();

	public String getException();

	BrokerAccount getAccount(String accountId) throws Exception;

	public DunkEventNode getEventNode();

	public List<BrokerAccount> getAccounts();

	public String getIdentifier();

	public DunkExecutor getExecutor();

	public BrokerBean getBrokerBean();
}

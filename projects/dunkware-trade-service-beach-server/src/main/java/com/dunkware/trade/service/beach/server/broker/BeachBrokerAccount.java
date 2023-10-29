package com.dunkware.trade.service.beach.server.broker;

import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerAccountEntity;

public interface BeachBrokerAccount {

	public long getId();

	public BeachBroker getBroker();

	public BeachBrokerAccountBean getBean();

	public BeachBrokerAccountEntity getEntity();

	public BrokerAccount getConnector();

}

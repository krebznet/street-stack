package com.dunkware.trade.service.beach.server.broker;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.broker.exception.BeachBrokerAccountNotFoundException;

import ca.odell.glazedlists.ObservableElementList;

public interface BeachBrokerService {

	DEventNode getEventNode(); 
	
	public ObservableElementList<BeachBrokerBean> getBrokerBeans();
	
	public ObservableElementList<BeachBrokerAccountBean> getBrokerAccountBeans();
	
	public BeachBrokerAccount getAccount(long id) throws BeachBrokerAccountNotFoundException;
	
	public void addBroker(AddBrokerReq req) throws UserException, Exception;
	
	public DExecutor getExecutor();
	
}

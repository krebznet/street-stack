package com.dunkware.trade.service.beach.server.broker;

import java.util.Collection;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnttity;

public interface BeachBroker {
	
	public void init(BeachBrokerEnttity entity);
	
	public BeachBrokerBean getBean(); 
	
	public DEventNode getEventNode();
	
	public long getId(); 
	
	public BeachBrokerEnttity getEntity();
	
	public Collection<BeachBrokerAccount> getAccounts();
	
	public String getIdentifier();
	

}

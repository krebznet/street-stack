package com.dunkware.trade.service.beach.server.broker.impl;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.server.broker.BeachBroker;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccount;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccountBean;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerAccountEntity;

public class BeachBrokerAccountImpl implements BeachBrokerAccount {
	
	
	private BrokerAccount connector; 
	
	private BeachBroker broker; 
	
	private BeachBrokerAccountEntity entity; 
	
	private DEventNode eventNode; 
	
	private BeachBrokerAccountBean bean; 
	
	public BeachBrokerAccountImpl() { 
		bean  = new BeachBrokerAccountBean();
		bean.setIdentifier("Loading");
		bean.setBrokerIdentifier("Loading");
	}
	
	public void init(BeachBrokerAccountEntity entity,  BeachBroker broker, BrokerAccount connector) { 
		this.entity = entity; 
		this.broker = broker; 
		this.connector = connector; 
		this.eventNode = broker.getEventNode().createChild(this);
		connector.getEventNode().addEventHandler(this);;
	}

	@Override
	public long getId() {
		return entity.getId();
	}

	@Override
	public BeachBroker getBroker() {
		return broker; 
	}

	@Override
	public BeachBrokerAccountBean getBean() {
		return bean; 
	}

	@Override
	public BeachBrokerAccountEntity getEntity() {
		return entity;
	}

	@Override
	public BrokerAccount getConnector() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

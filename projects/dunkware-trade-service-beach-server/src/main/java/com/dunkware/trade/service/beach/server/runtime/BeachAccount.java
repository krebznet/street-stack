package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.context.BeachTradeSpec;
import com.dunkware.trade.service.beach.server.entities.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entities.BeachPlayEnt;

public class BeachAccount {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired()
	private BeachRuntime runtime; 
	
	@Autowired()
	private ApplicationContext ac; 
	
	private ConcurrentHashMap<Long, BeachPlay> plays = new ConcurrentHashMap<Long, BeachPlay>();

	private BeachAccountEnt entity;
	private BeachBroker broker; 
	private BrokerAccount brokerAccount; 
	private DEventNode eventNode; 
	
	
	private BeanUpdater beanUpdater;
	
	public void load(BeachBroker broker, BeachAccountEnt ent) { 
		this.entity = ent; 
		this.broker = broker;
		eventNode = broker.getEventNode().createChild("/accounts/" + ent.getId());
		try {
			for (BeachPlayEnt playEnt : entity.getPlays()) {
				BeachPlay play = new BeachPlay();
				ac.getAutowireCapableBeanFactory().autowireBean(play);
				play.load(this, playEnt);
				plays.put(playEnt.getId(), play);
			}
		} catch (Exception e) {
			// okay. 
		}
		
	}
	
	public boolean canTrade(BeachTradeSpec spec) {
		return true;
	}
	
	public BeachAccountEnt getEntity() { 
		return entity;
	}
	
	public String getIdentifier() { 
		return entity.getIdentifier();
	}
	
	public BeachBroker getBroker() { 
		return broker; 
	}
	
	public BrokerAccount getConnection() {
		return brokerAccount;
	}
	
	public DEventNode getEventNode() { 
		return eventNode;
	}
	
	public Collection<BeachPlay> getPlays() { 
		return plays.values();
	}
	

	
	private class BeanUpdater extends Thread { 
		
		public void run() { 
			// activeCapital
			// tradedCapital
			// unreazliedPL
			// realizedPL
			// go through each play and do that. 
		}
	}
}

package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entities.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entities.BeachBrokerEnt;
import com.dunkware.trade.service.beach.server.entities.BeachRepo;

public class BeachBroker {
	
	@Autowired
	private BeachRuntime runtime;
	
	@Autowired
	private BeachRepo repo; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BeachBrokerEnt entity;
	private Map<Long,BeachAccount> accounts = new ConcurrentHashMap<Long,BeachAccount>();

	
	private BrokerType brokerType; 
	private Broker broker; 
	
	private DEventNode eventNode; 
	
	
	
	public void init(BeachBrokerEnt entity) throws Exception  { 
		this.entity = entity; 
		eventNode = runtime.getEventTree().getRoot().createChild("/brokers/" + entity.getIdentifier()); 
		try {
			brokerType = DJson.getObjectMapper().readValue(entity.getType(), BrokerType.class);
		} catch (Exception e) {
			logger.error("Exception deserializing broker type model in broker init " + e.toString());
			throw e; 
		}
		try {
			broker = TradeRegistry.get().createBroker(brokerType);
		} catch (Exception e) {
			logger.error("Exception creating broker type " + e.toString());
			throw e;
		}
		try {
			broker.connect(brokerType,eventNode,runtime.getExecutor());
			Thread.sleep(2500);
		} catch (Exception e) {
			// okay can't connect we need status update. 
		}
		if(isConnected()) { 
			for (BrokerAccount account : broker.getAccounts()) {
				// okay what if we have entity account not here? 
				// when do we create an account etc. 
			}
		}
		for (BeachAccountEnt accountEnt : entity.getAccounts()) {
			try {
				BeachAccount account = new BeachAccount();
				ac.getAutowireCapableBeanFactory().autowireBean(account);
				account.load(this, accountEnt);
				// put accounts 
			} catch (Exception e) {
				logger.error("Exception loading account " + e.toString());
			}
		}
	}
	
	public boolean isConnected() { 
		if(broker.getStatus() == BrokerStatus.Connected) {
			return true; 
		}
		return false; 
	}
	

	public Collection<BeachAccount> getAccounts() { 
		return accounts.values();
	}
	
	public Broker getConnector() { 
		return broker; 
	}
	
	public DEventNode getEventNode() { 
		return eventNode;
	}
	
	public String getIdentifier() { 
		return entity.getIdentifier();
	}
	
	public BeachBrokerEnt getEntity() { 
		return entity;
	}
	
	
}

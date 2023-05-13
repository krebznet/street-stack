package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;

public class BeachBroker {
	
	@Autowired
	private BeachRuntime runtime;
	
	@Autowired
	private BeachRepo repo; 
	
	@PersistenceContext(unitName = "trade")
	private EntityManager em;
	
	
	@Autowired
	private ApplicationContext ac; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BeachBrokerEnt entity;
	private Map<String,BeachAccount> accounts = new ConcurrentHashMap<String,BeachAccount>();

	
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
			logger.error("FIX ME: Broker connect exception " + e.toString()); 
		}
		loadAndSyncAccounts();
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
	
	/**
	 * Here we will create new BeachAccounts for any broker accounts that does not
	 * have a matching beach account Invoked upon broker connection established
	 */
	@Transactional
	private void loadAndSyncAccounts() {
		BrokerAccount[] brokerAccounts = broker.getAccounts();
		for (BrokerAccount brokerAccount : brokerAccounts) {
			// query an account
			BeachAccountEnt actEnt = repo.getAccount(brokerAccount.getIdentifier());
			if (actEnt == null) {
				logger.info("Broker {} Creating New Account {}", getEntity().getIdentifier(),brokerAccount.getIdentifier(),
						brokerType.getIdentifier());
				actEnt = new BeachAccountEnt();
				actEnt.setIdentifier(brokerAccount.getIdentifier());
				actEnt.setBroker(getEntity());
				try {
					em.persist(actEnt);
				} catch (Exception e) {
					logger.error("Fatal DB Error persisting new beach account " + e.toString(), e);
					continue;
				}
			} else {
				logger.info("Loading Broker Account {} On Broker {}", brokerAccount.getIdentifier(),
						brokerType.getIdentifier());
			}
			BeachAccount act = new BeachAccount();
			ac.getAutowireCapableBeanFactory().autowireBean(act);
			act.init(this,actEnt,brokerAccount);
			accounts.put(actEnt.getIdentifier(), act);
		}
		// now what we are not doing yet is what if we have a beach account that exists for this
		// broker but that account is no longer on the broker? Fuck IT! 
	}
	
	
}

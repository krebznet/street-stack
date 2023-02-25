package com.dunkware.trade.service.beach.server.runtime.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.repository.BeachBrokerDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachBroker;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@Component
@Profile("TradeService")
@Transactional
public class BeachServiceImpl implements BeachService  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ConcurrentHashMap<String, BeachBroker> brokers = new ConcurrentHashMap<String,BeachBroker>();
	
	
	@Autowired
	private BeachRuntime runtime;
	
	private DEventNode eventNode; 
	
	@PersistenceContext(unitName = "trade")
	private EntityManager em;
	
	@Autowired
	private BeachTradeRepo tradeRepo; 
	
	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	public void load() { 
		logger.info("Starting Trade Service");
		eventNode = runtime.getEventTree().getRoot().createChild("/trade");
		List<BeachBrokerDO> brokers = tradeRepo.getBrokers();
		for (BeachBrokerDO beachBrokerDO : brokers) {
			BeachBrokerImpl broker = new BeachBrokerImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(broker);
			try {
			//	broker.init(beachBrokerDO);				
				this.brokers.put(broker.getIdentifier(), broker);
				logger.info("{} Loaded During Trade Service Load ",broker.getIdentifier());
			} catch (Exception e) {
				logger.error("Broker {} Initialize Exception On Service Start {}",beachBrokerDO.getIdentifier(),e.toString() );
			}

		}
		
	}

	@Override
	public BeachBroker addBroker(BrokerType type) throws Exception {
		if(brokerExists(type.getIdentifier())) { 
			throw new Exception("Broker Identifier " + type.getIdentifier() + " already exists");
		}
		try {
			TradeRegistry.get().createBroker(type);
		} catch (Exception e) {
			throw new Exception("Broker Type Not Found In Registry " + e.toString());
		}
		
		BeachBrokerDO entity = new BeachBrokerDO();
		entity.setIdentifier(type.getIdentifier());
		entity.setType(DJson.serialize(type));
		
		try {
			em.persist(entity);	
		} catch (Exception e) {
			throw new Exception("Broker Entity Persist Failed Internal Fatal " + e.toString());
		} finally { 
		}
		BeachBrokerImpl broker = new BeachBrokerImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(broker);
		try {
			broker.init(entity);
		} catch (Exception e) {
			logger.error("Internal Add Broker Init Exception " + e.toString(),e);
			//throw new Exception("Internal Exception Adding Broker " + e.toString());
		}
		brokers.put(entity.getIdentifier(), broker);
		return broker;
	}

	@Override
	public BeachBroker[] getBrokers() throws Exception {
		return brokers.values().toArray(new BeachBroker[brokers.size()]);
	}

	@Override
	public boolean brokerExists(String identifier) {
		if(brokers.containsKey(identifier))
			return true;
		return false; 
	}

	@Override
	public BeachBroker getBroker(String identifier) throws Exception {
		if(brokerExists(identifier) == false) { 
			throw new Exception("Beach Broker " + identifier + " Does Not Exist");
		}
		return brokers.get(identifier);
	}

	
	@Override
	public BeachAccount getAccount(String broker, String account) throws Exception {
		for (BeachBroker beachBroker : brokers.values()) {
			for (BrokerAccount beachAccount : beachBroker.getAccounts()) {
				if(beachAccount.getIdentifier().equals(account)) { 
					return (BeachAccount)beachAccount;
				}
			}
		}
		throw new Exception("Beach Broker " + broker + " account " + account + " not found");
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	
	@Override
	public List<BeachAccount> getAccounts() {
		List<BeachAccount> accounts = new ArrayList<BeachAccount>();
		for (BeachBroker broker : brokers.values()) {
			for (BrokerAccount beachAccount : broker.getAccounts()) {
				accounts.add((BeachAccount)beachAccount);
			}
		}
		return accounts;
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}

package com.dunkware.trade.service.beach.server.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;

@Service
public class BeachService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Marker runtimeMarker = MarkerFactory.getMarker("beach.runtime");

	private ConcurrentHashMap<String, BeachBroker> brokers = new ConcurrentHashMap<String, BeachBroker>();

	private DEventNode eventNode;


	@Autowired
	private BeachRepo repo;
	

	@Autowired
	private ApplicationContext ac;
	
	
	@PostConstruct()
	private void load() { 
		Thread runner = new Thread() { 
			public void run() { 
				List<BeachBrokerEnt> brokers = repo.getBrokers();
				for (BeachBrokerEnt beachBrokerEnt : brokers) {
					BeachBroker broker = new BeachBroker();
					ac.getAutowireCapableBeanFactory().autowireBean(broker);
					try {
						broker.init(beachBrokerEnt);				
						BeachService.this.brokers.put(broker.getIdentifier(),broker);
					} catch (Exception e) {
						logger.error("Broker {} Initialize Exception On Service Start {}",beachBrokerEnt.getIdentifier(),e.toString() );
					}
				}
				
			}
		};
		runner.start();
	}
	
	@Transactional
	public BeachBroker addBroker(AddBrokerReq brokerAdd) throws Exception  { 
		if (brokerExists(brokerAdd.getName())) {
			throw new Exception("Broker " + brokerAdd.getName() + " already exists, cannot add");
		}
		//create the type from brokerAdd
		TwsBrokerType type = new TwsBrokerType();
		type.setConnectionId(1);
		type.setHost(brokerAdd.getHost());
		type.setPort(brokerAdd.getPort());
		type.setIdentifier(brokerAdd.getName());
		try {
			TradeRegistry.get().createBroker(type);
		} catch (Exception e) {
			throw new Exception("Broker Type Not Found In Registry " + e.toString());
		}

		BeachBrokerEnt entity = new BeachBrokerEnt();
		entity.setIdentifier(type.getIdentifier());
		entity.setType(DJson.serialize(type));

		try {
			EntityManager em = repo.createEntityManager();
			em.getTransaction().begin();
			
			
			em.persist(entity);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new Exception("Broker Entity Persist Failed Internal Fatal " + e.toString());
		} finally {
		}
		BeachBroker broker = new BeachBroker();
		ac.getAutowireCapableBeanFactory().autowireBean(broker);
		try {
			broker.init(entity);
		} catch (Exception e) {
			logger.error("Internal Add Broker Init Exception " + e.toString(), e);
		}
		brokers.put(entity.getIdentifier(), broker);
		return broker;
	}
	
	
	public BeachBroker[] getBrokers() throws Exception {
		return brokers.values().toArray(new BeachBroker[brokers.size()]);
	}

	
	public boolean brokerExists(String identifier) {
		if (brokers.containsKey(identifier))
			return true;
		return false;
	}

	
	public BeachBroker getBroker(String identifier) throws Exception {
		if (brokerExists(identifier) == false) {
			throw new Exception("Beach Broker " + identifier + " Does Not Exist");
		}
		return brokers.get(identifier);
	}

	public BeachAccount getAccount(long accountId) throws Exception { 
		for (BeachBroker beachBroker : brokers.values()) {
			for (BeachAccount beachAccount : beachBroker.getAccounts()) {
				if (beachAccount.getId() == accountId) {
					return (BeachAccount) beachAccount;
				}
			}
		}
		throw new Exception("Beach Account ID " + accountId + " not found");
	}
	
	public BeachAccount getAccount(String broker, String account) throws Exception {
		for (BeachBroker beachBroker : brokers.values()) {
			for (BeachAccount beachAccount : beachBroker.getAccounts()) {
				if (beachAccount.getIdentifier().equals(account)) {
					return (BeachAccount) beachAccount;
				}
			}
		}
		throw new Exception("Beach Broker " + broker + " account " + account + " not found");
	}

	
	public DEventNode getEventNode() {
		return eventNode;
	}


	public List<BeachAccount> getAccounts() {
		List<BeachAccount> accounts = new ArrayList<BeachAccount>();
		for (BeachBroker broker : brokers.values()) {
			for (BeachAccount beachAccount : broker.getAccounts()) {
				accounts.add((BeachAccount) beachAccount);
			}
		}
		return accounts;
	}
	
	
	public BeachStream getStream(String stream) throws Exception { 
		return null;
	}
	
	
}

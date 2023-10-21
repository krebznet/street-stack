package com.dunkware.trade.service.beach.server.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.event.EBeachAcccountInitialized;
import com.dunkware.trade.service.beach.server.event.EBeachBrokerInitialized;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

@Service
public class BeachService {

	// config.stream.enable=

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ConcurrentHashMap<Long, BeachBroker> brokers = new ConcurrentHashMap<Long, BeachBroker>();

	private ConcurrentHashMap<Long, BeachAccount> accounts = new ConcurrentHashMap<Long, BeachAccount>();

	private DEventNode eventNode;

	@Autowired
	private BeachRepo repo;

	@Autowired
	private BeachRuntime runtime;

	@Autowired
	private ApplicationContext ac;

	private ObservableElementList<BeachBrokerBean> brokerBeans = null;

	private ObservableElementList<BeachAccountBean> accountBeans = null;

	@PostConstruct()
	private void load() {
		brokerBeans = new ObservableElementList<BeachBrokerBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachBrokerBean>()),
				new ObservableBeanListConnector<BeachBrokerBean>());
		accountBeans = new ObservableElementList<BeachAccountBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachAccountBean>()),
				new ObservableBeanListConnector<BeachAccountBean>());

		eventNode = runtime.getEventTree().getRoot().createChild(this);
		eventNode.addEventHandler(this);

		List<BeachBrokerEnt> brokers = repo.getBrokers();
		for (BeachBrokerEnt beachBrokerEnt : brokers) {
			BeachBroker broker = new BeachBroker();
			ac.getAutowireCapableBeanFactory().autowireBean(broker);
			broker.init(beachBrokerEnt);
		}

	}

	@Transactional
	public BeachBroker addBroker(AddBrokerReq brokerAdd) throws Exception {
		if (brokerExists(brokerAdd.getName())) {
			throw new Exception("Broker " + brokerAdd.getName() + " already exists, cannot add");
		}
		// create the type from brokerAdd
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
			em.close();

		} catch (Exception e) {
			throw new Exception("Broker Entity Persist Failed Internal Fatal " + e.toString());
		} finally {
		}
		BeachBroker broker = new BeachBroker();
		ac.getAutowireCapableBeanFactory().autowireBean(broker);
		broker.init(entity);
		;
		return broker;
	}

	public Collection<BeachBroker> getBrokers() {
		return brokers.values();
	}

	public Collection<BeachAccount> getAccounts() {
		return accounts.values();
	}

	public BeachSystem getSystem(long id) throws Exception {
		for (BeachBroker broker : brokers.values()) {
			for (BeachAccount account : broker.getAccounts()) {
				for (BeachSystem system : account.getSystems()) {
					if (system.getId() == id) {
						return system;
					}
				}
			}
		}
		throw new Exception("System ID " + id + " not found");
	}

	public Collection<BeachSystem> getSystems() {
		List<BeachSystem> results = new ArrayList<BeachSystem>();
		for (BeachBroker broker : brokers.values()) {
			for (BeachAccount account : broker.getAccounts()) {
				results.addAll(account.getSystems());
			}
		}
		return results;

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

	public ObservableElementList<BeachBrokerBean> getBrokerBeans() {
		return brokerBeans;
	}

	public ObservableElementList<BeachAccountBean> getAccountBeans() {
		return accountBeans;
	}

	@ADEventMethod
	public void brokerInitialized(EBeachBrokerInitialized event) {
		if (logger.isDebugEnabled()) {
			logger.debug("Broker initialized event " + event.getBroker().getIdentifier());
		}
		brokers.put(event.getBroker().getId(), event.getBroker());
		brokerBeans.getReadWriteLock().writeLock().lock();
		brokerBeans.add(event.getBroker().getBean());
		brokerBeans.getReadWriteLock().writeLock().unlock();
	}

	@ADEventMethod
	public void beachAccountInitailized(EBeachAcccountInitialized event) {
		accounts.put(event.getAcccount().getId(), event.getAcccount());

		accountBeans.getReadWriteLock().writeLock().lock();
		accountBeans.add(event.getAcccount().getBean());
		accountBeans.getReadWriteLock().writeLock().unlock();
	}

}

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

import com.dunkware.common.util.databean.DataBeanConnector;
import com.dunkware.common.util.events.DEvent;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerEvent;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachAcccountInitialized;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachBrokerInitialized;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

@Service
public class BeachService {

	// config.stream.enable=

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ConcurrentHashMap<String, BeachStream> streams = new ConcurrentHashMap<String, BeachStream>();

	private ConcurrentHashMap<String, BeachBroker> brokers = new ConcurrentHashMap<String, BeachBroker>();

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
				new DataBeanConnector<BeachBrokerBean>());
		accountBeans = new ObservableElementList<BeachAccountBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachAccountBean>()),
				new DataBeanConnector<BeachAccountBean>());
		eventNode = runtime.getEventTree().getRoot().createChild(this);

		Thread runner = new Thread() {
			public void run() {
				String[] streamIdents = runtime.getStreamIdentifiers().split(",");

				for (String ident : streamIdents) {

					BeachStream stream = new BeachStream();
					ac.getAutowireCapableBeanFactory().autowireBean(stream);
					try {
						stream.init(ident);
						if (logger.isDebugEnabled()) {
							logger.debug("Initialized stream " + ident);
						}
						streams.put(stream.getIdentifier(), stream);
					} catch (Exception e) {
						logger.error("Exception Initializing Beach Stream " + ident + " " + e.toString()); // System.exit(-1);
																											// }
					}
				}

				List<BeachBrokerEnt> brokers = repo.getBrokers();
				for (BeachBrokerEnt beachBrokerEnt : brokers) {
					BeachBroker broker = new BeachBroker();
					ac.getAutowireCapableBeanFactory().autowireBean(broker);
					broker.getEventNode().addEventHandler(this);
					broker.init(beachBrokerEnt);
				}

			}
		};

		runner.start();
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
		broker.getEventNode().addEventHandler(this);
		broker.init(entity);
		;
		brokers.put(entity.getIdentifier(), broker);
		return broker;
	}

	public Collection<BeachBroker> getBrokers() {
		return brokers.values();
	}

	public boolean brokerExists(String identifier) {
		if (brokers.containsKey(identifier))
			return true;
		return false;
	}

	public BeachPlay getPlay(long playId) throws Exception {
		for (BeachBroker beachBroker : brokers.values()) {
			for (BeachAccount beachAccount : beachBroker.getAccounts()) {
				for (BeachPlay play : beachAccount.getPlays()) {
					if (play.getId() == playId) {
						return play;
					}
				}
			}
		}
		throw new Exception("Trade Play ID " + playId + " Not found");
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
		BeachStream results = streams.get(stream);
		if (results == null) {
			throw new Exception("Cannot find stream " + stream);
		}
		return results;
	}

	public ObservableElementList<BeachBrokerBean> getBrokerBeans() {
		return brokerBeans;
	}

	public ObservableElementList<BeachAccountBean> getAccountBeans() {
		return accountBeans;
	}

	@ADEventMethod
	public void brokerInitialized(EBeachBrokerInitialized event) {
		if(logger.isDebugEnabled()) { 
			logger.debug("Broker initialized event " + event.getBroker().getIdentifier());
		}
		brokerBeans.getReadWriteLock().writeLock().lock();
		brokerBeans.add(event.getBroker().getBean());
		brokerBeans.getReadWriteLock().writeLock().unlock();
	}

	@ADEventMethod
	public void beachAccountAdded(EBeachAcccountInitialized event) {
		accountBeans.getReadWriteLock().writeLock().lock();
		accountBeans.add(event.getAcccount().getBean());
		accountBeans.getReadWriteLock().writeLock().unlock();
	}

}

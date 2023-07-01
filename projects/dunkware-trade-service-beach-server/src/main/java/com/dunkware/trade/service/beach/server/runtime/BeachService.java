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
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachAccountAdded;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

@Service
public class BeachService {

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
	
	public static ObservableElementList<BeachBrokerBean> brokerBeans = new ObservableElementList<BeachBrokerBean>(GlazedLists.threadSafeList(new BasicEventList<BeachBrokerBean>()), new DataBeanConnector<BeachBrokerBean>());

	private ObservableElementList<BeachAccountBean> accountBeans = new ObservableElementList<BeachAccountBean>(GlazedLists.threadSafeList(new BasicEventList<BeachAccountBean>()), new DataBeanConnector<BeachAccountBean>());
	
	
	
	
	@PostConstruct()
	private void load() {
		eventNode = runtime.getEventTree().getRoot().createChild("/service");
		
		BeachBrokerBean b = new BeachBrokerBean();
		b.setAccounts(3);
		b.setId(3);
		b.setName("fuck");
		b.setStatus("sa");
		b.setSummary("msdfsd");
		brokerBeans.getReadWriteLock().writeLock().lock();
		brokerBeans.add(b);
		brokerBeans.getReadWriteLock().writeLock().unlock();
		
		Thread runner = new Thread() {
			public void run() {
				String[] streamIdents = runtime.getStreamIdentifiers().split(",");
				/*
				 * for (String ident : streamIdents) { BeachStream stream = new BeachStream();
				 * ac.getAutowireCapableBeanFactory().autowireBean(stream); try {
				 * stream.init(ident); streams.put(stream.getIdentifier(), stream); } catch
				 * (Exception e) { logger.error("Exception Initializing Beach Stream " + ident +
				 * " " + e.toString()); //System.exit(-1); }
				 * 
				 * }
				 */	
				
				List<BeachBrokerEnt> brokers = repo.getBrokers();
				for (BeachBrokerEnt beachBrokerEnt : brokers) {
					BeachBroker broker = new BeachBroker(runtime,beachBrokerEnt);
					ac.getAutowireCapableBeanFactory().autowireBean(broker);
					broker.getEventNode().addEventHandler(this);
					brokerBeans.getReadWriteLock().writeLock().lock();
					brokerBeans.add(broker.getBean());
					brokerBeans.getReadWriteLock().writeLock().unlock();
					try {
						broker.init();
						
					} catch (Exception e) {
						logger.error("Broker {} Initialize Exception On Service Start {}",
								beachBrokerEnt.getIdentifier(), e.toString());
					}
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

		} catch (Exception e) {
			throw new Exception("Broker Entity Persist Failed Internal Fatal " + e.toString());
		} finally {
		}
		BeachBroker broker = new BeachBroker(runtime,entity);
		ac.getAutowireCapableBeanFactory().autowireBean(broker);
		brokers.put(entity.getIdentifier(), broker);
		broker.getEventNode().addEventHandler(this);;
		brokerBeans.getReadWriteLock().writeLock().lock();
		brokerBeans.add(broker.getBean());
		brokerBeans.getReadWriteLock().writeLock().unlock();
	
		try {
			broker.init();
					
		} catch (Exception e) {
			logger.error("Internal Add Broker Init Exception " + e.toString(), e);
		}
	
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
		if(results == null) { 
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

	/**
	 * Any child objects we attach ourselves to we route the events.
	 * 
	 * @param event
	 */
	@ADEventMethod
	public void eventDispatch(DEvent event) {
		eventNode.event(event);
	}
	
	@ADEventMethod
	public void brokerEvent(EBrokerEvent event) { 
		
	}
	
	@ADEventMethod
	public void beachAccountAdded(EBeachAccountAdded event) {
		accountBeans.getReadWriteLock().writeLock().lock();
		accountBeans.add(event.getAcccount().getBean());
		accountBeans.getReadWriteLock().writeLock().unlock();
	}
	

}

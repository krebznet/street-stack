package com.dunkware.trade.service.beach.server.broker.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.broker.tws.TwsBrokerType;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.broker.BeachBroker;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccount;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerAccountBean;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerService;
import com.dunkware.trade.service.beach.server.broker.exception.BeachBrokerAccountNotFoundException;
import com.dunkware.trade.service.beach.server.common.BeachMarkers;
import com.dunkware.trade.service.beach.server.entity.BeachBrokerEnttity;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

@Service
public class BeachBrokerServiceImpl implements BeachBrokerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("BeachBrokers");
	
	@Autowired
	private ExecutorService executorService;
	
	@Autowired
	private EventService eventService; 
	
	@Autowired
	private ApplicationContext ac; 
	
	@Autowired
	private BeachRepo beachRepo; 
	
	private ObservableElementList<BeachBrokerBean> brokerBeans;
	private ObservableElementList<BeachBrokerAccountBean> accountBeans;
	
	private DEventNode eventNode; 
	
	private Map<Long,BeachBroker> brokers = new ConcurrentHashMap<Long,BeachBroker>();
	
	
	@PostConstruct
	private void init() { 
		brokerBeans = new ObservableElementList<BeachBrokerBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachBrokerBean>()),
				new ObservableBeanListConnector<BeachBrokerBean>());
		accountBeans = new ObservableElementList<BeachBrokerAccountBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachBrokerAccountBean>()),
				new ObservableBeanListConnector<BeachBrokerAccountBean>());

		eventNode = eventService.getEventRoot().createChild(this);
		eventNode.addEventHandler(this);

		List<BeachBrokerEnttity> brokers = beachRepo.getBrokers();
		for (BeachBrokerEnttity beachBrokerEnt : brokers) {
			BeachBroker broker = new BeachBrokerImpl();
			this.brokerBeans.add(broker.getBean());
			ac.getAutowireCapableBeanFactory().autowireBean(broker);;
			broker.init(beachBrokerEnt);
			this.brokers.put(beachBrokerEnt.getId(), broker);
		}
	}
	
	@Override
	public DExecutor getExecutor() {
		return executorService.get();
	}


	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public ObservableElementList<BeachBrokerBean> getBrokerBeans() {
		return brokerBeans;
	}

	@Override
	public ObservableElementList<BeachBrokerAccountBean> getBrokerAccountBeans() {
		return accountBeans;
	}

	@Override
	public BeachBrokerAccount getAccount(long id) throws BeachBrokerAccountNotFoundException {
		for (BeachBroker broker : brokers.values()) {
			for (BeachBrokerAccount account : broker.getAccounts()) {
				if(account.getId() == id) { 
					return account;
				}
			}
		}
		throw new BeachBrokerAccountNotFoundException("Account id " + id + " not found");
	}

	@Override
	public void addBroker(AddBrokerReq req) throws UserException, Exception {
		if (brokerExists(req.getName())) {
			throw new UserException("Broker " + req.getName() + " already exists, cannot add");
		}
		// create the type from brokerAdd
		TwsBrokerType type = new TwsBrokerType();
		type.setConnectionId(1);
		type.setHost(req.getHost());
		type.setPort(req.getPort());
		type.setIdentifier(req.getName());
		try {
			TradeRegistry.get().createBroker(type);
		} catch (Exception e) {
			throw new Exception("Broker Type Not Found In Registry " + e.toString());
		}

		BeachBrokerEnttity entity = new BeachBrokerEnttity();
		entity.setIdentifier(type.getIdentifier());
		entity.setType(DJson.serialize(type));

		try {
			EntityManager em = beachRepo.createEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			em.close();

		} catch (Exception e) {
			logger.error(BeachMarkers.systemException(), "failed to persist new broker " + e.toString()	);
			throw new Exception("Broker Entity Persist Failed Internal Fatal " + e.toString());
		} finally {
		}
		
		BeachBroker broker = new BeachBrokerImpl();
		this.brokerBeans.add(broker.getBean());
		ac.getAutowireCapableBeanFactory().autowireBean(broker);
		broker.init(entity);
		this.brokers.put(entity.getId(), broker);
	}
	
	
	private boolean brokerExists(String name) { 
		for (BeachBroker broker : brokers.values()) {
			if(broker.getIdentifier().equalsIgnoreCase(name)) { 
				return true; 
			}
		}
		return false; 
	}

}

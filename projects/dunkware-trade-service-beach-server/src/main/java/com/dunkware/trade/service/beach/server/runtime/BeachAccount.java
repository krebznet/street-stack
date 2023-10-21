package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.entity.BeachSystemEnt;
import com.dunkware.trade.service.beach.server.event.EBeachAcccountInitialized;
import com.dunkware.trade.service.beach.server.event.EBeachSystemInitialized;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class BeachAccount {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired()
	private BeachRuntime runtime;

	@Autowired()
	private BeachService beachService;

	@Autowired
	private BeachRepo repo;

	@Autowired()
	private ApplicationContext ac;

	private BeachAccountEnt entity;
	private BeachBroker broker;
	private BrokerAccount brokerAccount;
	private DEventNode eventNode;

	private BeachAccountBean bean;
	
	private ConcurrentHashMap<Long, BeachSystem> systems = new ConcurrentHashMap<Long, BeachSystem>();
	
	private ObservableElementList<BeachTradeBean> tradeBeans;
	private ObservableElementList<BeachOrderBean> orderBeans;
	private ObservableElementList<BeachSystemBean> systemBeans;

	public BeachAccount(BeachAccountEnt ent, BeachBroker broker, BrokerAccount brokerAccount) {
		this.entity = ent;
		this.broker = broker;
		this.brokerAccount = brokerAccount;
		bean = new BeachAccountBean();
		bean.setBroker(broker.getIdentifier());
		bean.setBrokerId((int) broker.getEntity().getId());
		bean.setId(ent.getId());
		bean.setName(ent.getIdentifier());
		bean.setId(ent.getId());
		bean.notifyUpdate();
		
		
		
		tradeBeans = new ObservableElementList<BeachTradeBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachTradeBean>()),
				new ObservableBeanListConnector<BeachTradeBean>());
		orderBeans = new ObservableElementList<BeachOrderBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachOrderBean>()),
				new ObservableBeanListConnector<BeachOrderBean>());
		systemBeans = new ObservableElementList<BeachSystemBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachSystemBean>()),
				new ObservableBeanListConnector<BeachSystemBean>());

		this.eventNode = broker.getEventNode().createChild(this);
		this.eventNode.addEventHandler(this);
	}

	public void init() {
		try {

			// okay we need to look for trading systems right? 
			for (BeachSystemEnt sysEnt : entity.getSystems()) {
				BeachSystem system = new BeachSystem();
				ac.getAutowireCapableBeanFactory().autowireBean(sysEnt);
				system.init(sysEnt, this);
				
			}
		} catch (Exception e) {

			logger.error("Exception Init Beach Play " + e.toString());
		}

		eventNode.event(new EBeachAcccountInitialized(this));

	}
	
	public Collection<BeachSystem> getSystems() { 
		return systems.values();
	}

	public void addSystem(BeachSystemModel model) throws Exception { 
		// validate unique name
		for (BeachSystem system : beachService.getSystems()) {
			if(system.getName().equalsIgnoreCase(model.getName())) { 
				throw new Exception("System name " + model.getName() + " must be unique");
			}
		}
		// now what do we do create the fuckin entity 
		BeachSystemEnt ent = new BeachSystemEnt(); 
		ent.setAccount(this.getEntity());
		ent.setName(model.getName());
		try {
			ent.setModel(DJson.serialize(model));	
		} catch (Exception e) {
			throw new Exception("Exception serializing system model " + e.toString());
		}
		try {
		EntityManager em  = repo.createEntityManager();
		em.getTransaction().begin();
		em.persist(ent);
		em.getTransaction().commit();
		em.close();
		} catch (Exception e) {
			throw new Exception("Internal excepton persisting system entity " + e.toString());
		}
		
		BeachSystem system = new BeachSystem();
		ac.getAutowireCapableBeanFactory().autowireBean(system);
		system.init(ent, this);
		
	}

	public BeachAccountBean getBean() {
		return bean;
	}

	public long getId() {
		return entity.getId();
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

	public ObservableElementList<BeachTradeBean> getTradeBeans() {
		return tradeBeans;
	}

	public ObservableElementList<BeachOrderBean> getOrderBeans() {
		return orderBeans;
	}

	public ObservableElementList<BeachSystemBean> getSystemBeans() {
		return systemBeans;
	}
	
	@ADEventMethod()
	public void systemInitialized(EBeachSystemInitialized event) { 
		logger.debug("account recieved system initialized " + event.getSystem().getName());
		this.systems.put(event.getSystem().getId(), event.getSystem());
		this.systemBeans.getReadWriteLock().writeLock().lock();
		this.systemBeans.add(event.getSystem().getBean());
		this.systemBeans.getReadWriteLock().writeLock().unlock();
	}
	
	//public void tradeInitialized(Ebeachtrade)
}

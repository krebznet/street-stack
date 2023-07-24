package com.dunkware.trade.service.beach.server.runtime;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.databean.DataBeanConnector;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.entity.BeachSystemEntity;
import com.dunkware.trade.service.beach.server.event.EBeachAcccountInitialized;

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

	private ObservableElementList<BeachTradeBean> tradeBeans;
	private ObservableElementList<BeachOrderBean> orderBeans;
	private ObservableElementList<BeachPlayBean> playBeans;

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
				new DataBeanConnector<BeachTradeBean>());
		orderBeans = new ObservableElementList<BeachOrderBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachOrderBean>()),
				new DataBeanConnector<BeachOrderBean>());
		playBeans = new ObservableElementList<BeachPlayBean>(
				GlazedLists.threadSafeList(new BasicEventList<BeachPlayBean>()),
				new DataBeanConnector<BeachPlayBean>());

		this.eventNode = broker.getEventNode().createChild(this);

	}

	public void init() {
		try {

		} catch (Exception e) {

			logger.error("Exception Init Beach Play " + e.toString());
		}

		eventNode.event(new EBeachAcccountInitialized(this));

	}

	public void addSystem(BeachSystemModel model) throws Exception { 
		// validate unique name
		for (BeachSystem system : beachService.getSystems()) {
			if(system.getName().equalsIgnoreCase(model.getName())) { 
				throw new Exception("System name " + model.getName() + " must be unique");
			}
		}
		// now what do we do create the fuckin entity 
		BeachSystemEntity ent = new BeachSystemEntity(); 
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

	public ObservableElementList<BeachTradeBean> getTradeBeans() {
		return tradeBeans;
	}

	public ObservableElementList<BeachOrderBean> getOrderBeans() {
		return orderBeans;
	}

	public ObservableElementList<BeachPlayBean> getPlayBeans() {
		return playBeans;
	}
}

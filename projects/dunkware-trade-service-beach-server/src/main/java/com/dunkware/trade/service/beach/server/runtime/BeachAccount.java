package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachAccountEnt;
import com.dunkware.trade.service.beach.server.entity.BeachPlayEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeSpec;

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
	
	private ConcurrentHashMap<Long, BeachPlay> plays = new ConcurrentHashMap<Long, BeachPlay>();

	private BeachAccountEnt entity;
	private BeachBroker broker; 
	private BrokerAccount brokerAccount; 
	private DEventNode eventNode; 
	
	private BeachAccountBean bean;
	
	public void init(BeachBroker broker, BeachAccountEnt ent, BrokerAccount brokerAccount) { 
		this.entity = ent; 
		this.broker = broker;
		this.brokerAccount = brokerAccount;
		bean = new BeachAccountBean();
		bean.setBroker(broker.getIdentifier());
		bean.setName(ent.getIdentifier());
		bean.setId(ent.getId());
		eventNode = broker.getEventNode().createChild("/accounts/" + ent.getId());
		try {
			for (BeachPlayEnt playEnt : entity.getPlays()) {
				BeachPlay play = new BeachPlay();
				ac.getAutowireCapableBeanFactory().autowireBean(play);
				play.init(this, playEnt);
				plays.put(playEnt.getId(), play);
			}
		} catch (Exception e) {
			logger.error("Exception Init Beach Play " + e.toString());
		}
		
	}
	
	
	public BeachAccountBean getBean() { 
		return bean;
	}
	
	public BeachPlay createPlay(Play model, String name) throws Exception { 
		for (BeachPlay play : plays.values()) {
			if(play.getEntity().getName().equals(name)) { 
				throw new Exception("Beach Play Name " + name + " already exists");
			}
		}
		BeachPlayEnt ent = new BeachPlayEnt();
		ent.setAccount(entity);
		ent.setModel(DJson.serialize(model));
		ent.setName(name);
		entity.getPlays().add(ent);
		try {
			EntityManager em = repo.createEntityManager();
			em.getTransaction().begin();
			em.persist(ent);
			em.getTransaction().commit();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception persisting Play Entity " + e.toString());
			throw e;
		}
		BeachPlay play = new BeachPlay();
		ac.getAutowireCapableBeanFactory().autowireBean(play);
		try {
			play.init(this, ent);
			this.plays.put(ent.getId(), play);
		} catch (Exception e) {
			logger.error("Exception init new trade play " + e.toString());
			throw e;
		}
		return play;
		
		
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
	
	public Collection<BeachPlay> getPlays() { 
		return plays.values();
	}
	

	
	private class BeanUpdater extends Thread { 
		
		public void run() { 
			// activeCapital
			// tradedCapital
			// unreazliedPL
			// realizedPL
			// go through each play and do that. 
		}
	}
}

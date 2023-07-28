package com.dunkware.trade.service.beach.server.runtime;

import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.model.trade.BeachTradeStatus;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.entity.BeachSystemEnt;
import com.dunkware.trade.service.beach.server.entity.BeachTradeEnt;
import com.dunkware.trade.service.beach.server.event.EBeachSystemInitialized;

public class BeachSystem {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private BeachAccount account; 
	
	private BeachSystemEnt entity; 
	
	private DEventNode eventNode; 
	
	private BeachSystemBean bean; 
	
	private String exception; 
	
	@Autowired
	private BeachRuntime runtime; 
	
	@Autowired
	private BeachRepo repo; 
	
	@Autowired
	private ApplicationContext ac;
	
	/**
	 * List of active trades. 
	 */
	private ConcurrentHashMap<String, BeachTrade> trades = new ConcurrentHashMap<String, BeachTrade>();
	
	private BeachSystemModel model = null;
	
	public void init(BeachSystemEnt ent, BeachAccount account) { 
		bean = new BeachSystemBean();
		bean.setAccountId(account.getId());
		bean.setAccountIdent(account.getIdentifier());
		bean.setAllocatedCapital(getId());
		bean.setStatus(BeachSystemStatus.Pending.name());
		this.account = account;
		this.entity = ent; 
		this.eventNode = account.getEventNode().createChild(this);
		this.eventNode.addEventHandler(this); 
		try {
			model = DJson.getObjectMapper().readValue(ent.getModel(), BeachSystemModel.class);
		} catch (Exception e) {
			exception = "Deserialize Model " + e.toString();
			bean.setException(exception);
			setStatus(BeachSystemStatus.Exception);
			eventNode.event(new EBeachSystemInitialized(this));
			return;
		}
		setStatus(BeachSystemStatus.Running);;
		eventNode.event(new EBeachSystemInitialized(this));		
	}
	
	
	public DEventNode getEventNode() { 
		return eventNode;
	}
	
	public BeachSystemBean getBean() { 
		return bean;
	}
	
	public void setStatus(BeachSystemStatus status) { 
		bean.setStatus(status.name());;
		bean.notifyUpdate();
		// async save here 
	}
	
	
	public BeachAccount getAccount() { 
		return account;
	}
	
	public long submitTrade(BeachTradeModel model) throws Exception { 
		logger.debug("Submit Trade Recieved " + DJson.serialize(model));
		BeachTradeEnt ent  = new BeachTradeEnt();
		ent.setSystem(entity);
		ent.setStatus(BeachTradeStatus.PendingSubmit);
		ent.setIdentifier(model.getSymbol() + ":" + nextTradeSequence());
		try {
			ent.setModel(exception);	
		} catch (Exception e) {
			throw new Exception("Exception serializing model " + e.toString());
		}
		try {
			EntityManager em = repo.createEntityManager();
			em.getTransaction().begin();
			em.persist(ent);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			bean.setException("Persist Exception " + e.toString());
			setStatus(BeachSystemStatus.Exception);
			throw new Exception("Internal Exception persisting trade " + e.toString());
		}
		
		BeachTrade trade = new BeachTrade();
		ac.getAutowireCapableBeanFactory().autowireBean(trade);
		try {
			trade.init(ent,this);	
		} catch (Exception e) {
			logger.error("Exception trade init " + e.toString(),e);
			// if it cannot initialize means what? 
			// well it could be a broker exxception
			// exception; 
			// when we need to delete it? 
			// TODO: handle exception
		}
		// lets build the trade identifier: 
		
		return 1;
	}
	
	public String getName() { 
		return entity.getName();
	}
	
	public long getId() { 
		return entity.getId();
	}
	
	public synchronized int nextTradeSequence() { 
		int seq = entity.getTradeSequence() + 1;
		asyncSave();
		return seq;
	}
	
	private void asyncSave() { 
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				try {
					EntityManager em = repo.createEntityManager();
					em.getTransaction().begin();
					em.merge(entity);
					em.getTransaction().commit();
				} catch (Exception e) {
					logger.error("Internal exception saving trade entity " + e.toString());
				}
			}
		};
		
		runtime.getExecutor().execute(runner);;
		
	}
	
}

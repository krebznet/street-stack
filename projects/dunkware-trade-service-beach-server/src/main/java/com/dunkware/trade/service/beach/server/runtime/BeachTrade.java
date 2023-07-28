package com.dunkware.trade.service.beach.server.runtime;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.model.trade.BeachTradeStatus;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.entity.BeachTradeEnt;

public class BeachTrade {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BeachTradeEnt entity; 
	private BeachSystem system; 
	
	private BeachTradeBean bean;
	
	private DEventNode eventNode; 
	private BeachTradeModel model; 
	
	@Autowired
	private BeachRuntime runtime; 
	
	@Autowired
	private BeachRepo repo; 
	
	private BeachTradeStatus status = BeachTradeStatus.PendingSubmit;
	
	public void init(BeachTradeEnt ent, BeachSystem system) { 
		
		bean = new BeachTradeBean();
		bean.setAccountIdent(system.getAccount().getIdentifier());;
		bean.setAccountId(system.getAccount().getId());
		bean.setSystemId(system.getId());
		bean.setSystemIdent(system.getName());
		this.entity = ent;
		this.system = system; 
		this.eventNode = system.getEventNode().createChild(this);
		try {
			model = DJson.getObjectMapper().readValue(entity.getModel(), BeachTradeModel.class);
		} catch (Exception e) {
			logger.error("Exception Deserilaizing Trade Model Hanlde me " + e.toString());
			ent.setException("init " + e.toString());
			bean.setException("init " + e.toString());;
			bean.setStatus(BeachTradeStatus.Exception.name());
			
			// BeachTrade ?
			return;
		}
		//if(entity.getStatus() == BeachTradeStatus.Pending) { 
			
		//}
		
	}
	
	public BeachTradeBean getBean() { 
		return bean;
	}
	
	public BeachTradeModel getModel() { 
		return model;
	}
	
	public DEventNode getEventNode() { 
		return eventNode;
	}
	
	public long getId() { 
		return entity.getId();
	}
	
	public BeachSystem getSystem() { 
		return system; 
		
	}
	
	private void startTrade() { 
		// so we need a object 
		// BeachTradeOpener
		// BeachTradeStop
		// BeachTradeCloser 
		
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

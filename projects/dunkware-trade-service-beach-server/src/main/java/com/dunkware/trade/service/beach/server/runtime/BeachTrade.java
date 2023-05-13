package com.dunkware.trade.service.beach.server.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.context.BeachTradeClose;
import com.dunkware.trade.service.beach.server.context.BeachTradeCloseFactory;
import com.dunkware.trade.service.beach.server.context.BeachTradeExitTrigger;
import com.dunkware.trade.service.beach.server.context.BeachTradeOpen;
import com.dunkware.trade.service.beach.server.context.BeachTradeOpenFactory;
import com.dunkware.trade.service.beach.server.context.BeachTradeSpec;
import com.dunkware.trade.service.beach.server.context.stop.BeachTradeStop;
import com.dunkware.trade.service.beach.server.entities.BeachRepo;
import com.dunkware.trade.service.beach.server.entities.BeachTradeEnt;
import com.dunkware.trade.tick.api.instrument.Instrument;

public class BeachTrade {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachRuntime runtime;
	
	@Autowired
	private BeachRepo repo; 

	@Autowired
	private ApplicationContext ac; 
	
	private BeachTradeOpen entry; 
	private BeachTradeClose exit; 
	
	private BeachPlay play;

	private BeachTradeEnt entity;

	private BeachTradeSpec spec;

	private BeachTradeStatus status = BeachTradeStatus.Created;

	private DEventNode eventNode;

	private String identifier;
	
	private List<BeachTradeExitTrigger> exitTriggers = new ArrayList<BeachTradeExitTrigger>();
	private Semaphore exitTriggerLock = new Semaphore(1);
	
	private BeachTradeStop tradeStop; 
	

	public void init(BeachTradeSpec spec, BeachPlay play) throws Exception{
		this.play = play;
		this.spec = spec;
		eventNode = play.getEventNode().createChild("/trades/idhere");
		
		entry = BeachTradeOpenFactory.create(play.getModel());
		ac.getAutowireCapableBeanFactory().autowireBean(entry);
		exit = BeachTradeCloseFactory.create(play.getModel());
		ac.getAutowireCapableBeanFactory().autowireBean(exit);
		
		entity = new BeachTradeEnt();
		entity.setPlay(play.getEntity());
		entity.setBroker(play.getAccount().getBroker().getEntity());
		entity.setAccount(play.getAccount().getEntity());
		entity.setAllocatedCapital(spec.getCapital());
		entity.setAllocatedSize(spec.getSize());
		entity.setTickerSymbol(spec.getInstrument().getTicker().getSymbol());
		entity.setTickerType(spec.getInstrument().getTicker().getType());;
		
		EntityManager em = repo.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Fatal Problem Persist Trade Failed " + e.toString(), e);
			throw new OrderException("Internal Error Persist Trade Failed " + e.toString(), e);
		} finally {
			em.close();
		}

	}
	
	public BeachTradeEnt getEntity() { 
		return entity;
	}

	public String getIdentifier() {
		return identifier;
	}

	public DEventNode getEventNode() {
		return eventNode;
	}

	public BeachTradeStatus getStatus() {
		return status;

	}

	public BeachPlay getPlay() {
		return play;
	}

	public Instrument getInstrument() {
		return spec.getInstrument();
	}

	public BeachOrder entryOrder(OrderType type, String log) throws Exception {
		BeachOrder order = new BeachOrder();
		ac.getAutowireCapableBeanFactory().autowireBean(order);
		order.create(this, "entry", log, type);
		// okay then what here? 
		return order;
	}

	BeachOrder createExitOrder(OrderType type, String log)throws Exception {
		BeachOrder order = new BeachOrder();
		ac.getAutowireCapableBeanFactory().autowireBean(order);
		order.create(this, "exit", log, type);
		// okay then what here? 
		return order;
	}

	public void open() {
		// check status 
		// okay need to listen here for even 
		// set openingTime time 
		entry.start(this);
	}

	void close() {
		// closing status? 
		// exitTriggers.stop();
		// tradeStop().cancel();
		// check status
		// setClosingTime
		exit.start(this);
	}
	
	public BeachTradeStop getStop() { 
		return null;
	}
	
	/**
	 * This is called by an exit trigger when a exit trigger is activated. 
	 * @param trigger
	 */
	public void exitTrigger(String trigger) { 
		// okay trigger, lets log the event 
		// start the exit and put trade in closing status 
	}
	
	
	
	// Event Handlers 
	
	public void entryException(Object event) { 
		
	}
	
	public void exitException(Object event) { 
		
	}
	
	public void exitCompleted(Object event) { 
		// this will be sent by the TradeExit or the TradeStop
	}
	
	public void entryCompleted(Object event) { 
		// now lets start the TradeExitManager
		// 
	}
	
	public void stopSubmitted(Object event) { 
		
	}
	
	public void stopCancelled(Object event) { 
		
	}
	
	
	
	
	

}

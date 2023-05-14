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
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.service.beach.protocol.play.PlayExitTrigger;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.entity.BeachTradeEnt;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeEntry;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeEntryFactory;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExit;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExitFactory;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExitTrigger;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeExitTriggerFactory;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeSpec;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeClose;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeEntryComplete;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeEntryException;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeExitComplete;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeExitException;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeOpen;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeUpdate;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class BeachTrade {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachRuntime runtime;

	@Autowired
	private BeachRepo repo;

	@Autowired
	private ApplicationContext ac;

	private BeachTradeEntry entry;
	private BeachTradeExit exit;

	private BeachPlay play;

	private BeachTradeEnt entity;

	private BeachTradeSpec spec;

	private BeachTradeStatus status = BeachTradeStatus.Created;

	private DEventNode eventNode;

	private String identifier;

	private List<BeachTradeExitTrigger> exitTriggers = new ArrayList<BeachTradeExitTrigger>();
	private Semaphore exitTriggerLock = new Semaphore(1);

	private BeachTradeBean bean;

	private TradeTickerSpec tickerSpec;
	
	
	public void init(BeachTradeSpec spec, BeachPlay play) throws Exception {
		this.play = play;
		this.spec = spec;
		eventNode = play.getEventNode().createChild("/trades/idhere");
		bean = new BeachTradeBean();
		bean.setAccount(play.getAccount().getIdentifier());
		bean.setPlay(play.getEntity().getName());

		entry = BeachTradeEntryFactory.create(play.getModel());
		ac.getAutowireCapableBeanFactory().autowireBean(entry);
		exit = BeachTradeExitFactory.create(play.getModel());
		ac.getAutowireCapableBeanFactory().autowireBean(exit);
		
		for (PlayExitTrigger exitTriggerModel : play.getModel().getExitTriggers()) {
			try {
				BeachTradeExitTrigger exitTrigger = BeachTradeExitTriggerFactory.create(exitTriggerModel);
				ac.getAutowireCapableBeanFactory().autowireBean(exitTrigger);
				exitTrigger.init(this, exitTriggerModel);
				exitTriggers.add(exitTrigger);
			} catch (Exception e) {
				throw new Exception("Trade Init Exit Trigger Exception " + e.toString());
			}
		}
		
		int tradeSequence = play.nextTradeSequence(spec.getTickerSpec().getSymbol());
		identifier = spec.getTickerSpec().getSymbol().toUpperCase() + "-" + tradeSequence;
		entity = new BeachTradeEnt();
		entity.setIdentifier(identifier);
		entity.setPlay(play.getEntity());
		entity.setBroker(play.getAccount().getBroker().getEntity());
		entity.setAccount(play.getAccount().getEntity());
		entity.setAllocatedCapital(spec.getCapital());
		entity.setAllocatedSize(spec.getSize());
		entity.setTickerSymbol(spec.getInstrument().getTicker().getSymbol());
		entity.setTickerType(spec.getInstrument().getTicker().getType());
		;
		
		// validate and create exit triggers
		for (PlayExitTrigger model : play.getModel().getExitTriggers()) {
			try {
				BeachTradeExitTrigger trigger = BeachTradeExitTriggerFactory.create(model);
				ac.getAutowireCapableBeanFactory().autowireBean(trigger);
				trigger.init(this, model);
				exitTriggers.add(trigger);
			} catch (Exception e) {
				throw new Exception("Beach Exit Trigger creation failed " + e.toString());
			}
		}

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
	
	
	public boolean isActive() { 
		if(status == BeachTradeStatus.Closing || status == BeachTradeStatus.Open || status == BeachTradeStatus.Opening) { 
			return true;
		}
		return false;
	}
	
	public void open() { 
		status = BeachTradeStatus.Opening;
		entity.setOpeningTime(BeachRuntime.dateTime());
		entity.setStatus(status);
		entry.getEventNode().addEventHandler(this);
		entry.start(this);
		persistAsync();
		eventNode.event(new EBeachTradeUpdate(this));
		
	}

	public BeachTradeSpec getTradeSpec() {
		return spec;
	}

	public TradeTickerSpec getTickerSpec() {
		return tickerSpec;
	}

	public BeachAccount getAccount() {
		return play.getAccount();
	}

	public BeachTradeBean getBean() {
		return bean;
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

	BeachOrder createExitOrder(OrderType type, String log) throws Exception {
		BeachOrder order = new BeachOrder();
		ac.getAutowireCapableBeanFactory().autowireBean(order);
		order.create(this, "exit", log, type);
		// okay then what here?
		return order;
	}

	
	public void close(String trigger) {
		status = BeachTradeStatus.Closing;
		entity.setExitTrigger(trigger);
		entity.setClosingTime(BeachRuntime.dateTime());
		persistAsync();
		exit.start(this);
	}

	public void logInfo(String message) {
		
	}
	
	public void logError(String message) { 
		
	}
	
	public void logTrace(String message) { 
		
	}
	
	/**
	 * Async Persist In Another Thread it will persist the trade entity
	 */
	private void persistAsync() {
		PersistRunnable runnable = new PersistRunnable();
		runtime.getExecutor().execute(runnable);
	}

	private class PersistRunnable implements Runnable {

		public PersistRunnable() {

		}

		public void run() {
			EntityManager em = null;
			try {
				// entity.setLastUpdate(entity.getCancelTime());
				// entity.setLastStatus(getSpec().getStatus());
				// entity.setFilled(getSpec().getFilled());

				em = repo.createEntityManager();
				em.getTransaction().begin();
				em.merge(entity);
				// em.persist(entity);
				em.getTransaction().commit();
			} catch (Exception e) {
				logger.error("Broker Order Persist Runnable Exception " + e.toString(), e);
			} finally {
				em.close();
			}
		}
	}

	// Event Handlers

	/**
	 * Okay handle the fact that it could not create entry
	 * 
	 * @param event
	 */
	@ADEventMethod
	public void entryException(EBeachTradeEntryException event) {
		this.status = BeachTradeStatus.EntryException;
		entity.setEntryException(event.getException());
		entity.setStatus(this.status);
		// now what?
	}
	
	/**
	 * Okay the trade entry is completed 
	 * @param event
	 */
	@ADEventMethod
	public void entryComplete(EBeachTradeEntryComplete event) {
		status = BeachTradeStatus.Open;
		entity.setOpenTime(BeachRuntime.dateTime());
		entity.setStatus(status);
		entity.setFilledSize(event.getEntry().getFilledSize());
		entity.setEntryCommission(event.getEntry().getCommission());
		persistAsync();
		eventNode.event(new EBeachTradeOpen(this));
		for (BeachTradeExitTrigger beachTradeExitTrigger : exitTriggers) {
			beachTradeExitTrigger.start();
		}
		
		// now we need to do the exit triggers 
	}

	@ADEventMethod
	public void exitException(EBeachTradeExitException event) {
		status = BeachTradeStatus.ExitException;
		entity.setStatus(status);
		entity.setExitException(event.getError());
		// EBeachException - the real alert - can't exit a trade that is no good. 
		// notify problem; ExitException - Error - 
	}

	@ADEventMethod
	public void exitComplete(EBeachTradeExitComplete event) {
		status = BeachTradeStatus.Closed;
		entity.setClosedTime(BeachRuntime.dateTime());
		entity.setExitCommission(event.getExit().getCommission());
		entity.setCommission(entity.getExitCommission() + entity.getEntryCommission());
		entity.setStatus(status);
		persistAsync();
		eventNode.event(new EBeachTradeClose(this));
		// now unsubsribe the Insturment
		this.play.getAccount().getConnection().getBroker().releaseInstrument(spec.getInstrument());
	}



}

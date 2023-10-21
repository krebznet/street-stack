package com.dunkware.trade.service.beach.server.runtime.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.EntryStatus;
import com.dunkware.trade.sdk.core.model.trade.TradeSpec;
import com.dunkware.trade.sdk.core.model.trade.TradeStatus;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.sdk.core.runtime.trade.TradeContext;
import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeClosing;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryCompleted;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryException;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeExitCompleted;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeSpecUpdate;
import com.dunkware.trade.service.beach.protocol.spec.BeachTradeSpec;
import com.dunkware.trade.service.beach.server.repository.BeachEntryDO;
import com.dunkware.trade.service.beach.server.repository.BeachExitDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachOrder;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.api.instrument.InstrumentListener;



public class BeachTradeImpl implements BeachTrade, InstrumentListener {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachTradeRepo tradeRepo;

	

	private BeachTradeDO entity;

	private BeachEntryImpl entry;
	private BeachExitImpl exit;

	private BeachTradeSpec spec;

	private Instrument instrument;

	private DEventNode eventNode;

	private BeachBot bot;

	private List<BeachOrder> orders = new ArrayList<BeachOrder>();
	private Semaphore orderLock = new Semaphore(1);
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	



	@Override
	public BeachTradeDO getEntity() {
		return entity;
	}

	@Override
	public void create(BeachBot bot, String play, TradeType type) throws Exception {
		this.bot = bot; 
		try {
			instrument = bot.getInstrument(type.getTicker());
		} catch (Exception e) {
			throw new Exception("Exception creating trade, Could not get trading instrument for " + type.getTicker().toString() + " exception "
					+ e.toString());
		}
		TradeEntry tradeEntry = null;
		try {
			tradeEntry = TradeRegistry.get().createTradeEntry(type.getEntry());
		} catch (Exception e) {
			throw new Exception("Trade create exception trade entry create failed " + e.toString());
		}
		try {
			tradeEntry.init(type.getEntry());
		} catch (Exception e) {
			throw new Exception("Trade Entry init exception " + e.toString());
		}
		TradeExit tradeExit = null;
		try {
			tradeExit = TradeRegistry.get().createTradeExit(type.getExit());
		} catch (Exception e) {
			throw new Exception("Trade create exception exit create fiald " + e.toString());
		}
		try {
			tradeExit.init(type.getExit());
		} catch (Exception e) {
			throw new Exception("Trade exit init exception " + e.toString());
		}
		
	
		// compute allocated size
		double capital = type.getCapital();
		double lastPrice = instrument.getLast();
		double doubleSize = capital / lastPrice;
		int intSize = (int) doubleSize;
		
		// now create entities 
		
		entity = new BeachTradeDO(); 
		entity.setAllocatedCapital(type.getCapital());
		entity.setAllocatedSize(intSize);
		entity.setTickerSymbol(type.getTicker().getSymbol());
		entity.setBot(bot.getEntity());
		entity.setPlay(play);
		
		BeachEntryDO entryEnt = new BeachEntryDO();
		entryEnt.setTrade(entity);
		entryEnt.setType(DJson.serialize(type.getEntry()));
		entryEnt.setStatus(EntryStatus.Initialized);
		entity.setEntry(entryEnt);
		
		
		entry = new BeachEntryImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(entry);
		entry.init(entryEnt, tradeEntry);
		
		BeachExitDO exitEnt = new BeachExitDO();
		exitEnt.setTrade(entity);
		exitEnt.setType(DJson.serialize(type.getExit()));
		exitEnt.setType(DJson.serialize(type.getExit()));
		entity.setExit(exitEnt);
		
		exit = new BeachExitImpl(); 
		ac.getAutowireCapableBeanFactory().autowireBean(exit);
		exit.init(exitEnt, tradeExit);

	
		try {
			tradeRepo.persist(entity);
		} catch (Exception e) {
			throw new Exception("Internal Exception Persisting Trade Entity " + e.toString());
		} finally {

		}
		
		eventNode = bot.getEventNode().createChild("/trades/" + entity.getId());
		
		spec = new BeachTradeSpec();
		spec.setStatus(TradeStatus.Allocated);
		spec.setType(type);
		spec.setAllocatedSize(intSize);
		
	}

	@Override
	public BeachAccount getAccount() {
		return bot.getAccount();
	}

	@Override
	public void open() throws Exception {
		if (spec.getStatus() != TradeStatus.Allocated) {
			throw new Exception("Cannot Open Trade When Status Is " + getStatus());
		}
		entry.start(this);
		this.entry.getEventNode().addEventHandler(this);

	}

	@Override
	public void discard() throws Exception {
		// TODO: discard shit

	}

	@Override
	public void close() throws Exception {
		if (getSpec().getStatus() != TradeStatus.Open) {
			throw new Exception("Cannot Close Trade When Status Is " + getStatus());
		}
	}

	@Override
	public TradeStatus getStatus() {
		return getSpec().getStatus();
	}

	@Override
	public TradeSpec getSpec() {
		return spec;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public TradeType getType() {
		return spec.getType();
	}

	@Override
	public TradeEntry getEntry() {
		return entry;
	}

	@Override
	public TradeExit getExit() {
		return exit;
	}

	@Override
	public Instrument getInstrument() {
		return instrument;
	}

	/**
	 * Events Handler Methods
	 * 
	 * @param completed
	 */

	@ADEventMethod()
	public void entryCompleted(ETradeEntryCompleted completed) {
		// start the exit right?
		getSpec().setStatus(TradeStatus.Open);
		// getSpec().set
		instrument.addListener(this);
		exit.start(this);
		exit.getEventNode().addEventHandler(this);
		entity.setOpenTime(LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)));
		entity.setAvgEntryPrice(entry.getSpec().getAvgFillPrice());
		entity.setFilledSize(entry.getSpec().getFilledSize());
		saveEntity();
	}

	@ADEventMethod()
	public void entryException(ETradeEntryException exception) {
		// -- okay here what? 
		entity.setException("Entry Exception " + exception.getEntry().getSpec().getException());
		// then what? 
		saveEntity();
		System.err.println("Handle entry exception in beach trade impl ");
	}
	
	
	@ADEventMethod()
	public void exitCompleted(ETradeExitCompleted completed) {
		entity.setClosedTime(LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)));
		entity.setTotalCommission(entry.getSpec().getCommission() + exit.getSpec().getCommission());
		saveEntity();
		instrument.removeListener(this);
	}
	
	@ADEventMethod()
	public void tradeClosing(ETradeClosing closing) { 
		entity.setClosingTime(LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)));
		saveEntity();
	}

	/**
	 * Instrument Listener & TradeUpdater
	 */
	@Override
	public void instrumentUpdate(Instrument instrument) {
		getContext().execute(new TradeUpdater());
	}

	@Override
	public TradeContext getContext() {
		return bot;
	}

	private class TradeUpdater implements Runnable {

		public void run() {
			double fillPrice = getSpec().getEntry().getAvgFillPrice();
			double lastPrice = instrument.getLast();
			double upl = DCalc.getPercentageChange(lastPrice, fillPrice);
			getSpec().setUnrealizedPL(upl);
			ETradeSpecUpdate update = new ETradeSpecUpdate(BeachTradeImpl.this);
			getEventNode().event(update);
		}
	}

	@Override
	public BeachBot getBot() {
		return bot;
	}

	@Override
	public Order createEntryOrder(OrderType type) throws Exception {
		BeachOrder order = getAccount().createBeacEntryOrder(getBot(), entry, this, type);
		try {
			orderLock.acquire();
			orders.add(order);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			orderLock.release();

		}
		return order;
	}

	@Override
	public Order createExitOrder(OrderType type) throws Exception {
		BeachOrder order = getAccount().createBeachExitOrder(bot, exit, this, type);
		try {
			orderLock.acquire();
			orders.add(order);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			orderLock.release();

		}
		return order;
	}

	@Override
	public String getSymbol() {
		return entity.getTickerSymbol();
	}
	
	
	private void saveEntity() { 
		
		Thread saver = new Thread() { 
			
			public void run() { 
				try {
					EntityManager em = tradeRepo.createEntityManager();
					try {
						em.getTransaction().begin();
						em.merge(entity);
						
						em.getTransaction().commit();
					} catch (Exception e) {
						throw new Exception("Exception Persisting Entity " + e.toString());
					} finally { 
						em.close();
					}
				} catch (Exception e) {
					logger.error("Exception saving beach trade entity " + e.toString());
					
				}
			}
		};
		saver.start();
		
	}

}

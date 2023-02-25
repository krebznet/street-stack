package com.dunkware.trade.service.beach.server.runtime.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.trade.TradeSpec;
import com.dunkware.trade.sdk.core.model.trade.TradeStatus;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.sdk.core.runtime.trade.TradeContext;
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
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.api.instrument.InstrumentListener;

public class BeachTradeImpl implements BeachTrade, InstrumentListener {

	@Autowired
	private ApplicationContext ac; 
	
	@Autowired
	private BeachTradeRepo tradeRepo; 
	
	private BeachAccount account; 
	
	private BeachTradeDO entity; 
	
	
	private BeachEntryImpl entry; 
	private BeachExitImpl exit; 
	
	private BeachTradeSpec spec; 
	
	private Instrument instrument; 
	
	private DEventNode eventNode; 
	
	/**
	 * Have to keep in mind we could init a trade that is already open
	 * hence the difference between this method and create() setup is
	 * @param entity
	 * @throws Exception
	 */
	public void init(BeachTradeDO entity, boolean created) throws Exception { 
		
	}
	
	

	@Override
	public void create(TradeType type, TradeContext context) throws Exception {
		// try to get the instrument
		try {
			//instrument = pool.getAccount().getBroker().acquireInstrument(type.getTicker());
		} catch (Exception e) {
			throw new Exception("Could not get trading instrument for " + type.getTicker().toString() + " exception " + e.toString());
		}
		// set trade type 
		spec.setType(type);
		
		// compute allocated size 
		double capital = type.getCapital();
		double lastPrice = instrument.getLast();
		double doubleSize = capital/lastPrice;
		int intSize = (int)doubleSize;
		//spec.setCapitalAllocated(intSize);
	//	spec.setAllocatedSize(intSize);
		spec.setStatus(TradeStatus.Allocated);
		
		this.entity.setAllocatedCapital(type.getCapital());
		this.entity.setAllocatedSize(intSize);
		
		// need to create a entity
		try {
			TradeEntry entry = TradeRegistry.get().createTradeEntry(type.getEntry());
			this.entry = new BeachEntryImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(this.entry);
			
			BeachEntryDO entryEnt = new BeachEntryDO();
			entryEnt.setTrade(entity);
			entryEnt.setType(DJson.serialize(type.getEntry()));
			entity.setEntry(entryEnt);
			this.entry.init(entryEnt, entry); 
			getSpec().setEntry(entry.getSpec());
		
		} catch (Exception e) {
			throw new Exception("Trade Entry Create Failed " + e.toString());
		}
		try {
			TradeExit exit  = TradeRegistry.get().createTradeExit(type.getExit());
			BeachExitDO exitEnt = new BeachExitDO();
			exitEnt.setTrade(entity);
			exitEnt.setType(DJson.serialize(type.getExit()));
			entity.setExit(exitEnt);
			this.exit = new BeachExitImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(exitEnt);
			this.exit.init(exitEnt, exit);
			getSpec().setExit(exit.getSpec());
			this.exit.init(type.getExit());
		} catch (Exception e) {
			throw new Exception("Trade Exit Create Failed " + e.toString());
		}
		
		
		try {
			tradeRepo.persist(entity);
		} catch (Exception e) {
			throw new Exception("Internal Exception Persisting Trade Entity " + e.toString());
		} finally { 
		
		}
	}
	
	@Override
	public BeachAccount getAccount() {
		return account;
	}

	@Override
	public void open() throws Exception {
		if(spec.getStatus() != TradeStatus.Allocated) { 
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
		if(getSpec().getStatus() != TradeStatus.Open) { 
			throw new Exception("Cannot Close Trade When Status Is " + getStatus());
		}
	}

	@Override
	public TradeStatus getStatus() {
		return getSpec().getStatus();
	}

	@Override
	public TradeSpec getSpec() {
		return getSpec(); 
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
	 * @param completed
	 */
	
	@ADEventMethod()
	public void entryCompleted(ETradeEntryCompleted completed) { 
		// start the exit right?
		getSpec().setStatus(TradeStatus.Open);
	//	getSpec().set
		instrument.addListener(this);
		exit.start(this);
		exit.getEventNode().addEventHandler(this);
	}
	

	@ADEventMethod()
	public void entryException(ETradeEntryException exception) { 
		// todo: 
		System.err.println("Handle entry exception in beach trade impl ");
	}
	
	public void exitCompleted(ETradeExitCompleted completed) { 
		// close trade; 
		// kinda hard to get this right here
		// 
		instrument.removeListener(this);
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
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	
	


	
	
	


}

package com.dunkware.trade.service.beach.server.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.context.BeachTradeSpec;
import com.dunkware.trade.service.beach.server.entity.BeachPlayEnt;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.signal.StreamSignal;
import com.dunkware.xstream.model.signal.StreamSignalListener;

public class BeachPlay implements StreamSignalListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachRuntime runtime;

	@Autowired
	private BeachService beachService; 
	
	@Autowired
	private ApplicationContext ac;

	private BeachAccount account;
	private BeachPlayEnt entity;
	private Play model;

	private DEventNode eventNode;

	private List<BeachTrade> trades = new ArrayList<BeachTrade>();
	private Semaphore tradeLock = new Semaphore(1);

	private BeachPlayStatus status = BeachPlayStatus.Stopped;
	
	private BlockingQueue<StreamSignal> signalQueue = new LinkedBlockingQueue<StreamSignal>();
	
	private SignalHandler signalHandler; 

	void init(BeachAccount account, BeachPlayEnt ent) {
		this.account = account;
		this.entity = ent;
		account.getEventNode().createChild("/plays/" + ent.getId());
		try {
			model = DJson.getObjectMapper().readValue(ent.getModel(), Play.class);
		} catch (Exception e) {
			// log : set status;
			// it needs to reset open trades.
			//
		}
		// start a session
	}

	void start() throws Exception {
		beachService.getStream("us_equity").addSignalListener(this, getModel().getSignal());
		status = BeachPlayStatus.Running;
		signalHandler = new SignalHandler();
		signalHandler.start();
		
	}

	public BeachPlayEnt getEntity() {
		return entity;
	}

	public Play getModel() {
		return model;
	}

	public long getId() { 
		return entity.getId();
	}
	void stop() throws Exception {
		if(status != BeachPlayStatus.Running) { 
			throw new Exception("Beach Play cannot be stopped in status " + status);
		}
		signalHandler.interrupt();
		beachService.getStream("us_equity").removeSignalListener(this);
		
		try {
			// summer 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// okay so how do we close it here then right? 
	}

	public boolean canTrade(BeachTradeSpec spec) {
		if (!account.canTrade(spec)) {
			return false;
		}
		return true;
	}

	public void trade(BeachTradeSpec spec)  {
		BeachTrade trade = new BeachTrade();
		ac.getAutowireCapableBeanFactory().autowireBean(trade);
		try {
			trade.init(spec, this);	
			this.trades.add(trade);
			trade.open();
		} catch (Exception e) {
			// logger
			// TODO: handle exception
		}
		
	}

	public BeachPlayStatus getStatus() {
		return status;
	}

	public DEventNode getEventNode() {
		return eventNode;
	}

	public BeachAccount getAccount() {
		return account;
	}

	public BeachBroker getBroker() {
		return account.getBroker();
	}

	public BeachTradeSpec createTradeSpec(StreamSignal signal) throws Exception{
		BeachTradeSpec spec = new BeachTradeSpec();
		spec.setCapital(getModel().getTradeAllocation());
		spec.setSide(getModel().getSide());
		TradeTickerSpec ticker = new TradeTickerSpec();
		Instrument instrument = null;
		
		try {
			instrument = getAccount().getConnection().getBroker().acquireInstrument(ticker);
			
		} catch (Exception e) {
			throw e;
		}
		spec.setInstrument(instrument);
		return spec;
	}

	@Override
	public void onSignal(StreamSignal signal) {
		signalQueue.add(signal);
	}
	
	/**
	 * This is where we'll process a signal, and if we can trade it 
	 * we open the trade here. 
	 *
	 */
	private class SignalHandler extends Thread  {
		
		public void run() { 
			while(!interrupted()) { 
				try {
					StreamSignal signal = signalQueue.take();
					// first check if we have reached active capital or trade limit
					// if so don't bother subscribing on tws. 
					try {
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					BeachTradeSpec spec = createTradeSpec(signal);
					if(!canTrade(spec)) { 
						continue; 
						// log
					}
					trade(spec);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	}
			

}

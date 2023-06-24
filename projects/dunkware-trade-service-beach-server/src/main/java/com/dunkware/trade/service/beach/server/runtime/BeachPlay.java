package com.dunkware.trade.service.beach.server.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.databean.DataBeanConnector;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.entity.BeachPlayEnt;
import com.dunkware.trade.service.beach.server.entity.BeachRepo;
import com.dunkware.trade.service.beach.server.entity.BeachTradeSeqEnt;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeSpec;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class BeachPlay implements BeachSignalListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachRuntime runtime;
	
	@Autowired
	private BeachRepo repo; 

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
	
	private String exception = null;

	private BlockingQueue<BeachSignal> signalQueue = new LinkedBlockingQueue<BeachSignal>();

	private SignalHandler signalHandler;
	
	private BeachPlayBean bean; 

	private BeachStream stream; 
	
	private ObservableElementList<BeachTradeBean> tradeBeans;
	private ObservableElementList<BeachOrderBean> orderBeans;
	
	void init(BeachAccount account, BeachPlayEnt ent) {
		tradeBeans = new ObservableElementList<BeachTradeBean>(GlazedLists.threadSafeList(new BasicEventList<BeachTradeBean>()), new DataBeanConnector<BeachTradeBean>());
		orderBeans = new ObservableElementList<BeachOrderBean>(GlazedLists.threadSafeList(new BasicEventList<BeachOrderBean>()), new DataBeanConnector<BeachOrderBean>());
		this.account = account;
		this.entity = ent;
		bean = new BeachPlayBean();
		bean.setName(ent.getName());
		bean.setAccount(account.getIdentifier());
		try {
			model = DJson.getObjectMapper().readValue(ent.getModel(), Play.class);
		} catch (Exception e) {
			status = BeachPlayStatus.Exception;
			exception = "Error deserializing play model " + e.toString();
		}
		try {
			this.stream = beachService.getStream(model.getStream());
		} catch (Exception e) {
			status = BeachPlayStatus.Exception;
			this.exception = e.toString();
		}
		bean.setStatus(status.name());
		bean.setException(exception);
		account.getEventNode().createChild("/plays/" + ent.getId());
		
		// start a session
	}

	public void start() throws Exception {
		if(status != BeachPlayStatus.Stopped) { 
			throw new Exception("Play cannot be started in status " + status.name());
		}
		try {
			BeachStream stream = beachService.getStream("us_equity");
			beachService.getStream("us_equity").addSignalListener(this, getModel().getSignal());
			
		} catch (Exception e) {
			bean.setStatus(BeachPlayStatus.Exception.name());
			bean.setException(e.getMessage());
			bean.notifyUpdate();
			status = BeachPlayStatus.Exception;
			
			throw new Exception("Exception adding signal listener to stream " + e.toString());
		}
		status = BeachPlayStatus.Running;
		signalHandler = new SignalHandler();
		signalHandler.start();

	}
	
	
	
	public ObservableElementList<BeachTradeBean> getTradeBeans() {
		return tradeBeans;
	}

	public ObservableElementList<BeachOrderBean> getOrderBeans() {
		return orderBeans;
	}

	public String getName() { 
		return entity.getName();
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
	
	public List<BeachTrade> getTrades() { 
		try {
			tradeLock.acquire();
			return trades;
		} catch (Exception e) {
			return new ArrayList<BeachTrade>();
		} finally { 
			tradeLock.release();
		}
	}

	void stop() throws Exception {
		if (status != BeachPlayStatus.Running) {
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

	/**
	 * Initiate Trade 
	 * @param signal
	 * @throws Exception
	 */
	public void trade(BeachSignal signal) throws Exception {
		BeachTradeSpec spec = new BeachTradeSpec();
		spec.setCapital(getModel().getTradeAllocation());
		spec.setSide(getModel().getSide());
		int size = (int) Math.floor((double)model.getTradeAllocation() / signal.getLast());
		spec.setSize(size);
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setSymbol(signal.getSymbol());
		ticker.setType(TradeTickerType.Equity);
		spec.setTickerSpec(ticker);
		Instrument instrument = null;
		try {
			instrument = getAccount().getConnection().getBroker().acquireInstrument(ticker);
		} catch (Exception e) {
			// okay issue here now with that. 
			throw e;
		}
		spec.setInstrument(instrument);
		// now that we are trading. 
		BeachTrade trade = new BeachTrade();
		ac.getAutowireCapableBeanFactory().autowireBean(trade);
		try {
			trade.init(spec, this);
			try {
				tradeLock.acquire();
				this.trades.add(trade);
			} catch (Exception e) {
			} finally { 
				tradeLock.release();
			}
			trade.open();
			trade.getEventNode().addEventHandler(this);
		} catch (Exception e) {
			// okay this would be a system or play configuration problem here. 
			// open trade exception 
		}
	}
	
	
	public BeachPlayBean getBean() { 
		return bean; 
	}
	

	@Override
	public void onSignal(BeachSignal signal) {
		signalQueue.add(signal);
	}
	
	/**
	 * before we get an instrument from a signal lets see if we can 
	 * trade in the first place. 
	 * @param symbol
	 * @return
	 */
	public boolean canTrade(BeachSignal signal) { 
		// trade allocation + allocated capital > deny 
		if(bean.getActiveCapital() + model.getTradeAllocation() > model.getAllocatedCapital()) { 
			// log me
			return false; 
		}
		
		// active trade limit reach deny 
		if(model.isEnableActiveTradeLimit()) { 
			if(bean.getActiveTrades() == model.getActiveTradeLimit() || bean.getActiveTrades() > model.getActiveTradeLimit()) { 
				return false; 
			}
		}
		
		// active symbol limit reached deny 
		if(model.isEnableActiveSymbolLimit()) { 
			int active = activeSymbolTradeCount(signal.getSymbol());
			if(model.getActiveSymbolLimit() == active ||active > model.getActiveSymbolLimit()) { 
				return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * Returns the next sequence for a symbol trade within the scope of this trade play
	 * @param symbol
	 * @return
	 */
	public int nextTradeSequence(String symbol) { 
		BeachTradeSeqEnt seqEnt = null;
		for (BeachTradeSeqEnt seq : entity.getTradeSequences()) {
			if(seq.getSymbol().equalsIgnoreCase(symbol)) { 
				seqEnt = seq;
				break;
			}
		}
		if(seqEnt == null) { 
			seqEnt = new BeachTradeSeqEnt();
			seqEnt.setPlay(entity);
			seqEnt.setSequence(1);
			seqEnt.setSymbol(symbol.toUpperCase());
			entity.getTradeSequences().add(seqEnt);
		} else { 
			seqEnt.setSequence(seqEnt.getSequence() + 1);
		}
		// either way need to persist the SequenceEntity. 
		EntityManager em = null;
		try {
		
			em = repo.createEntityManager();
			em.getTransaction().begin();
			em.merge(seqEnt);
			// em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception persisting Trade Sequence" + e.toString(), e);
		} finally {
			em.close();
		}
		
		return seqEnt.getSequence();
		
		
	}
	
	public int activeSymbolTradeCount(String symbol) { 
		int count = 0;
		try {
			tradeLock.acquire();
			for (BeachTrade trade : trades) {
				if(trade.getBean().getSymbol().equalsIgnoreCase(symbol)) { 
					count++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			tradeLock.release();
		}
		return count;
	}
	
	/*****************************************************
	 * ------ Trade Event Handler Methods --------------- 
	 *****************************************************/
	
	
	/**
	 * Trade Update makes us want to update our bean 
	 * @param updateEvent
	 */
	public void tradeUpdate(Object updateEvent) { 
		
	}
	
	/**
	 * Trade is closed - do we care? 
	 * @param updateEvent
	 */
	public void tradeClosed(Object updateEvent) { 
		
	}
	
	/**
	 * Not sure
	 * @param exceptionEvent
	 */
	public void tradeException(Object exceptionEvent) { 
		
	}

	/**
	 * This is where we'll process a signal, and if we can trade it we open the
	 * trade here.
	 *
	 */
	private class SignalHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					BeachSignal signal = signalQueue.take();
					if(canTrade(signal)) { 
						trade(signal);
					}
				} catch (Exception e) {
					// logger here? 
				}
			}
		}

	}
	
	
	private class BeanUpdater extends Thread { 
		
		public void run() {
			
			// unrealizedPL
			// realizedPL 
			// activeCapital
			// tradedCapital
			// active
		}
		
	}

}

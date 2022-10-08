package com.dunkware.trade.service.beach.server.trade.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeStatus;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.registry.TradeRegistry;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeException;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.system.BeachSystem;
import com.dunkware.trade.service.beach.server.trade.BeachAccount;
import com.dunkware.trade.service.beach.server.trade.BeachOrder;
import com.dunkware.trade.service.beach.server.trade.BeachService;
import com.dunkware.trade.service.beach.server.trade.BeachSession;
import com.dunkware.trade.service.beach.server.trade.BeachTrade;
import com.dunkware.trade.service.beach.server.trade.entity.BeachSessionDO;
import com.dunkware.trade.service.beach.server.trade.entity.BeachTradeDO;

public class BeachSessionImpl implements BeachSession {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BeachService tradeService; 
	
	
	@Autowired
	private BeachRuntime beachRuntime; 
	
	
	@Autowired
	private ApplicationContext ac;
	

	
	private BeachAccount account;
	private BeachSessionDO entity;
	

	private List<BeachOrderImpl> orders = new ArrayList<BeachOrderImpl>();
	private Semaphore orderLock = new Semaphore(1);
	
	
	private List<BeachTradeImpl> trades = new ArrayList<BeachTradeImpl>();
	private Semaphore tradeLock = new Semaphore(1);
	
	
	private DEventNode eventNode; 
	
	
	public void init(BeachSystem system) throws Exception { 
		// create a new session
		entity = new BeachSessionDO();
		
		
		
		/*
		 * this.entity = entity; eventNode =
		 * tradeService.getEventNode().createChild("/pools/" + entity.getIdentifier());
		 * try { account =
		 * tradeService.getAccount(entity.getAccount().getBroker().getIdentifier(),
		 * entity.getAccount().getIdentifier());
		 * spec.setStatus(BeachPoolStatus.EXCEPTION); } catch (Exception e) {
		 * logger.warn("Beach Pool {} Init Exception, Account Get Exception " +
		 * e.toString(),entity.getIdentifier()); }
		 * spec.setStatus(BeachPoolStatus.RUNNING);
		 */
	}
	
	
	
	@Override
	public void event(String source, String type, String message) throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public BeachTrade createTrade(TradeType tradeType) throws Exception {
		// validate trade
		validateTradeType(tradeType);
		BeachTradeImpl trade = new BeachTradeImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(trade);
		
		BeachTradeDO ent = new BeachTradeDO();
		ent.setPool(entity);
		ent.setTickerSymbol(tradeType.getTicker().getSymbol());
		ent.setTickerType(tradeType.getTicker().getType());
		ent.setAllocatedCapital(tradeType.getCapital());
		ent.setStatus(TradeStatus.Initializing);
	//	trade.init(ent, this,false);
		trade.create(tradeType, this);
		
		try {
			tradeLock.acquire();
			trades.add(trade);
		} catch (Exception e) {
			
		} finally { 
			tradeLock.release();
		}
		
		return trade;
	}
	
	@Override
	public Order createOrder(OrderType type) throws TradeException {
		try {
			return account.createOrder(type);	
		} catch (Exception e) {
			
			throw new TradeException(e.toString(),e);
		}
		
	}


	@Override
	public void execute(Runnable runnable) {
		beachRuntime.getExecutor().execute(runnable);
		
	}
	
	

	@Override
	public Collection<BeachTrade> getTrades() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Collection<BeachOrder> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public BeachSessionDO getEntity() {
		return entity;
	}



	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}
	
	private void validateTradeType(TradeType type) throws Exception { 
		if(type.getSide() == null) { 
			throw new Exception("Invalid Trade Type Side Is Null");
		}
		if(type.getEntry() == null) { 
			throw new Exception("Invalid Trade Type Entry Is Null");
		}
		if(type.getExit() == null) {
			throw new Exception("Invalid Trade Type Exit Is Null");
		}
		try {
			TradeRegistry.get().createTradeEntry(type.getEntry());
		} catch (Exception e) {
			throw new Exception("Trade Entry Type " + type.getEntry().getClass().getName() + " not found in registry");
		}
		try {
			TradeRegistry.get().createTradeExit(type.getExit()); 
		} catch (Exception e) {
			throw new Exception("Trade Exit Type " + type.getExit().getClass().getName() + " not found in registry");
		}
		if(type.getCapital() == 0.0 || type.getCapital() < 0.0)  {
			throw new Exception("Trade Capital Is Invalid " + type.getCapital() );
		}
	}


	@Override
	public Collection<Trade> getOpenTrades() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void liquidate() throws TradeException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Stream getStream(String ident) throws TradeException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public BeachSystem getSystem() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	
	
}

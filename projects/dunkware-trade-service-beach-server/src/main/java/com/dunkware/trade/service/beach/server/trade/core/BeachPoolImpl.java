package com.dunkware.trade.service.beach.server.trade.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolStatus;
import com.dunkware.trade.service.beach.protocol.trade.pool.spec.BeachPoolSpec;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.trade.BeachAccount;
import com.dunkware.trade.service.beach.server.trade.BeachPool;
import com.dunkware.trade.service.beach.server.trade.BeachTrade;
import com.dunkware.trade.service.beach.server.trade.BeachTradeService;
import com.dunkware.trade.service.beach.server.trade.entity.BeachPoolDO;
import com.dunkware.trade.service.beach.server.trade.entity.BeachTradeDO;

public class BeachPoolImpl implements BeachPool {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BeachTradeService tradeService; 
	
	@Autowired
	private BeachRuntime beachRuntime; 
	
	
	@Autowired
	private ApplicationContext ac;
	
	private BeachAccount account;
	private BeachPoolDO entity;
	
	private BeachPoolSpec spec = new BeachPoolSpec();
	
	private List<BeachTradeImpl> trades = new ArrayList<BeachTradeImpl>();
	private Semaphore tradeLock = new Semaphore(1);
	
	
	private DEventNode eventNode; 
	
	
	public void init(BeachPoolDO entity) throws Exception { 
		this.entity = entity;
		eventNode =  tradeService.getEventNode().createChild("/pools/" + entity.getIdentifier());
		try {
			account = tradeService.getAccount(entity.getAccount().getBroker().getIdentifier(), entity.getAccount().getIdentifier());
			spec.setStatus(BeachPoolStatus.EXCEPTION);
		} catch (Exception e) {
			logger.warn("Beach Pool {} Init Exception, Account Get Exception " + e.toString(),entity.getIdentifier());
		}
		spec.setStatus(BeachPoolStatus.RUNNING);
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
		trade.init(ent, this,false);
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
	public Order createOrder(OrderType type) throws Exception {
		return account.createOrder(type);
	}


	@Override
	public void execute(Runnable runnable) {
		beachRuntime.getExecutor().execute(runnable);
		
	}


	@Override
	public BeachAccount getAccount() {
		return account;
	}

	@Override
	public BeachPoolDO getEntity() {
		return entity;
	}


	@Override
	public BeachPoolStatus getStatus() {
		return spec.getStatus();
	}


	@Override
	public BeachPoolSpec getSpec() {
		return spec;
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
	
	
	
	

	
	
}

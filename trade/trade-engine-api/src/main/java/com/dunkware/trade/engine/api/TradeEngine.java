package com.dunkware.trade.engine.api;

import java.util.List;

import com.dunkware.trade.engine.model.api.node.TradeStrategyType;
import com.dunkware.trade.engine.model.bean.TradeBean;
import com.dunkware.trade.engine.model.bean.TradeOrderBean;
import com.dunkware.trade.engine.model.trade.TradeRequest;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;

import ca.odell.glazedlists.CompositeList;

public interface TradeEngine {
	
	public void start(TradeEngineStarter starter) throws Exception;
	
	DunkEventNode getEventNode();
	
	DunkExecutor getExecutor();
		
	CompositeList<TradeBean> getTradeBeans();
	
	CompositeList<Trade> getTrades();
	
	CompositeList<TradeOrderBean> getOrderBeans();
	
	CompositeList<TradeOrder> getOrders();
	
	CompositeList<TradeStrategy> getStrategies();
	
	CompositeList<TradeValidator> getTradeValidators();
	
	public void validateTradeRequest(TradeRequest request, List<String> errors);

	public TradeStrategy createStrategy(TradeStrategyType type) throws Exception;

}

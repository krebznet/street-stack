package com.dunkware.trade.engine.core.system;

import java.util.List;

import com.dunkware.trade.broker.api.Order;
import com.dunkware.trade.engine.api.Trade;
import com.dunkware.trade.engine.api.context.TradeContext;
import com.dunkware.trade.engine.api.system.TradeSystem;
import com.dunkware.trade.engine.model.api.TradeSystemType;
import com.dunkware.utils.core.events.DunkEventNode;

public class TradeSystemImpl implements TradeSystem {

	@Override
	public DunkEventNode getEventNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TradeContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trade> getTrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start(TradeSystemType model, String TwsHost, int twsPort, int twsClientId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}

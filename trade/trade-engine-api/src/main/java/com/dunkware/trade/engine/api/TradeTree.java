package com.dunkware.trade.engine.api;

import java.util.List;

import com.dunkware.trade.broker.api.Account;
import com.dunkware.trade.engine.model.api.TradeNodeType;

public interface TradeTree {

	public Account getAccount();
	
	public List<TradeValidator> getTradeValidators();
	
	public TradeNodeStrategy createNode(TradeNodeType type) throws Exception;
	
}

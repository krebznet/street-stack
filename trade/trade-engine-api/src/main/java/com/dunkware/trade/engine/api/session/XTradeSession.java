package com.dunkware.trade.engine.api.session;

import java.util.List;

import com.dunkware.trade.engine.api.session.model.XTradePolicy;

public interface XTradeSession {

	List<XTrade> getTrades();
	
	public XTradeStrategy createPool(XTradePolicy config) throws Exception;
	
	
	
	
}

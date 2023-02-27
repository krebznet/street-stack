package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.service.beach.server.repository.BeachTradeDO;

public interface BeachTrade extends Trade {

	BeachAccount getAccount();
	
	BeachBot getBot();

	BeachTradeDO getEntity();
	
	void create(BeachBot bot, String play, TradeType tradeType) throws Exception; 
	

}

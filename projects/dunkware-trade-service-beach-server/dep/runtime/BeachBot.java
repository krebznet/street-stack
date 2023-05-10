package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeContext;
import com.dunkware.trade.service.beach.protocol.spec.BeachBotState;
import com.dunkware.trade.service.beach.server.repository.BeachBotDO;
import com.dunkware.trade.service.beach.server.runtime.core.bot.BeachBotPlay;

public interface BeachBot extends TradeContext {
	
	void start() throws Exception; 
	
	void stop() throws Exception; 
	
	BeachBotDO getEntity();
	
	BeachBotState getState(); 
	
	DEventNode getEventNode();
	
	BeachAccount getAccount();
	
	Trade createPlayTrade(BeachBotPlay play, TradeType type) throws Exception;

}

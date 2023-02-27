package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;

import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.service.beach.server.repository.BeachAccountDO;

import comm.dunkware.trade.service.beach.web.bot.WebBot;

public interface BeachAccount extends BrokerAccount {
	
	String getIdentifier();
	
	BeachAccountDO getEntity();	
	
	Collection<BeachBot> getBots();
	
	BeachBot getBot(String identifier) throws Exception;
	
	BeachOrder createBeacEntryOrder(BeachBot bot, BeachEntry entry, BeachTrade trade, OrderType type) throws Exception;
	
	BeachOrder createBeachExitOrder(BeachBot bot, BeachExit exit, BeachTrade trade, OrderType type) throws Exception; 
	
	BeachBot createBot(WebBot model, String identifier) throws Exception; 
	
	void deleteBot(String identifier);
}

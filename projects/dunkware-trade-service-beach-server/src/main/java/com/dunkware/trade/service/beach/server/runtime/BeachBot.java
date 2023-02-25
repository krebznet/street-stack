package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.protocol.spec.BeachBotState;
import com.dunkware.trade.service.beach.server.repository.BeachBotDO;

public interface BeachBot {
	
	void start() throws Exception; 
	
	void stop() throws Exception; 
	
	BeachBotDO getEntity();
	
	BeachBotState getState(); 
	
	DEventNode getEventNode();
	
	BeachAccount getAccount();

}

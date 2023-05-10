package com.dunkware.trade.service.beach.server.trade.exit;

import com.dunkware.trade.service.beach.protocol.play.PlayExit;

public interface BeachTradeExit {

	void init(PlayExit exit);
	
	void lockRequest(); 
	
	void dispose(); 
	
	void lockGrant();
}

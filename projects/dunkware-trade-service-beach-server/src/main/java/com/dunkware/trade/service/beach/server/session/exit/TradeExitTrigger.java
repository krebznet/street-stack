package com.dunkware.trade.service.beach.server.session.exit;

public interface TradeExitTrigger {

	void init(Object model, TradeExit exit) throws Exception; 
	
	void lockAcquired();
	
	void lockGranted();
	
	void dispose(); 
	
	
}

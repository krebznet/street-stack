package com.dunkware.trade.service.beach.server.trade.exit;

import java.util.List;

import com.dunkware.trade.sdk.core.runtime.play.Play;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class BeachPlay {

	void init(Play play, BeachAccount account) throws Exception { 
		
	}
	
	void start() { 
		
	}
	
	void stop() { 
		
	}
	
	Object bean() { 
		return null;
	}
	
	List<BeachTrade> activeTrades() { 
		return null;
	}
	
	int lastTradeStart(String symbol) { 
		return 0; // 0 if not traded
	}
	
	int getTradeCount(String symbol) { 
		return 0;
	}
	
	boolean canTrade(Object signalEntity) {
		// look at active symbol limit
		return false; 
	}
	
	void newTrade(String symbol) { 
		// create a BechTrade
		// pass the play 
		// 
	}
	
	
	// signal listener -> 
}

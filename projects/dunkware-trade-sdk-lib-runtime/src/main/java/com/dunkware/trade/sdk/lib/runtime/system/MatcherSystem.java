package com.dunkware.trade.sdk.lib.runtime.system;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.system.System;
import com.dunkware.trade.sdk.core.runtime.system.SystemContext;
import com.dunkware.trade.sdk.core.runtime.system.SystemException;
import com.dunkware.trade.sdk.lib.model.system.MatcherSystemType;

public class MatcherSystem implements System  {

	private MatcherSystemType myType; 
	private SystemContext sysContext; 
	
	// this is a queue of shit. 
	private BlockingQueue<String> tradeOffers = new LinkedBlockingQueue<String>();
	
	@Override
	public void init(SystemType type, SystemContext context) throws SystemException {
		myType = (MatcherSystemType)type;
		sysContext = context; 
		// more work on trade context to get helper methods openTradeCount(by symbol); 
			// totalTradeCount(by symbol); 
		
		// setup s StreamClient; 
		// setup a thread that does the entity search and returns new hits
		// setup a thread that will offer the 
	}

	@Override
	public void resume(SystemType type, SystemContext context) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() { // dispose method; 
		// TODO Auto-generated method stub
		
	}
	
	private class StreamConnection extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				
			}
		}
	}

	
}

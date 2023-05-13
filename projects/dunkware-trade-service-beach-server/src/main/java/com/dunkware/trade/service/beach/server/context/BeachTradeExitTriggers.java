package com.dunkware.trade.service.beach.server.context;

public class BeachTradeExitTriggers {

	private BeachTradeSpec spec; 
	
	public void init(BeachTradeSpec spec) throws Exception { 
		this.spec = spec; 
		// say fuck it? - not sure 
		// stop - saftey stop
	}
	
	public void exitTrigger(String log) { 
		// this will start the default close to beging 
	}
	
	public void start() { 
		
	}
	
	public void stop() { 
		// cancels any triggers that might have stop orders and other things 
		
	}
}

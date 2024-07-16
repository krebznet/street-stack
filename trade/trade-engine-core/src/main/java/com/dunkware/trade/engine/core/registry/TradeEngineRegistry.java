package com.dunkware.trade.engine.core.registry;

public class TradeEngineRegistry {

	private static TradeEngineRegistry instance = null;
	
	public static TradeEngineRegistry get() { 
		if(instance == null) 
			instance = new TradeEngineRegistry();
		return instance;
			
	}
	
	
	
	private TradeEngineRegistry() { 
		
	}
	
	
	
}

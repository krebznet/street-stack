package com.dunkware.trade.engine.api.system;

import com.dunkware.trade.engine.api.TradeEngine;
import com.dunkware.trade.engine.model.api.TradeSystemType;

public interface TradeSystem extends TradeEngine {
	
	public void start(TradeSystemType model, String TwsHost, int twsPort, int twsClientId) throws Exception; 

}

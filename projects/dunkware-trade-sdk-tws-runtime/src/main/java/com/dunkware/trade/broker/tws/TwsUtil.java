package com.dunkware.trade.broker.tws;

import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.ib.client.Contract;
import com.ib.contracts.StkContract;

public class TwsUtil {
	
	
	public static Contract tickerToContract(TradeTickerSpec ticker) throws OrderException  { 
		if(ticker.getType() == TradeTickerType.Equity) {
			return new StkContract(ticker.getSymbol());	
		}
		throw new OrderException("Tws tickerToContract cannot create other types fix me ");
	}

}

package com.dunkware.trade.broker.tws;

import com.dunkware.trade.broker.api.runtime.OrderException;
import com.dunkware.trade.feed.model.ticker.Ticker;
import com.dunkware.trade.feed.model.ticker.TickerType;
import com.ib.client.Contract;
import com.ib.contracts.StkContract;

public class TwsUtil {
	
	
	public static Contract tickerToContract(Ticker ticker) throws OrderException  { 
		if(ticker.getType() == TickerType.Equity) {
			
			return new StkContract(ticker.getSymbol());	
		}
		throw new OrderException("Tws tickerToContract cannot create other types fix me ");
	}

}

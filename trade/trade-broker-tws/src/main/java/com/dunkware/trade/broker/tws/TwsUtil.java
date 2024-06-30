package com.dunkware.trade.broker.tws;

import com.dunkware.trade.api.data.ticker.Ticker;
import com.dunkware.trade.api.data.ticker.TickerType;
import com.dunkware.trade.broker.api.OrderException;
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

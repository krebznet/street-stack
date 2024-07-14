package com.dunkware.trade.broker.api.builders;

import com.dunkware.trade.api.data.ticker.Ticker;
import com.dunkware.trade.broker.api.OrderAction;
import com.dunkware.trade.broker.api.OrderSpec;
import com.dunkware.trade.broker.api.OrderType;
import com.dunkware.utils.core.json.DunkJson;

public class BrokerOrderSpecs {
	
	
	public static OrderSpec equityMarketBuy(String symbol, int size) { 
		OrderSpec spec = new OrderSpec();
		spec.setAction(OrderAction.BUY);
		spec.setType(OrderType.MKT);
		spec.setTicker(Ticker.usEquity(symbol));
		spec.setSize(size);
		
		try {
			System.out.println(DunkJson.serializePretty(spec));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return spec;
	}
	
	
	public static void main(String[] args) {
		equityMarketBuy("AAPL", 399);
	}

}

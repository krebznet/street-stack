package com.dunkware.trade.broker.api.runtime;

import com.dunkware.trade.broker.api.model.order.OrderKind;
import com.dunkware.trade.broker.api.model.order.OrderSide;
import com.dunkware.trade.broker.api.model.order.OrderType;
import com.dunkware.utils.core.json.DunkJson;

public class OrderTypeBuilder {
	
	
	public static OrderType equityMarketBuy(String symbol, int size) { 
		OrderType spec = new OrderType();
		spec.setSide(OrderSide.BUY);
		spec.setKind(OrderKind.MKT);
		//spec.setTicker(Ticker.usEquity(symbol));
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

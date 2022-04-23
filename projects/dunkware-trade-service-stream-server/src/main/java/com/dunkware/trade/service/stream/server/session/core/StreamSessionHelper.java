package com.dunkware.trade.service.stream.server.session.core;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.XStreamExtensionType;

public class StreamSessionHelper {
	
	
	public static List<List<TradeTickerSpec>> nodeTickers(int nodeCount, List<TradeTickerSpec> tickers) { 
		List<List<TradeTickerSpec>> results = new ArrayList<List<TradeTickerSpec>>();
		int i = 0; 
		while(i < nodeCount) { 
			List<TradeTickerSpec> list = new ArrayList<TradeTickerSpec>();
			results.add(list);
			i++;
		}
		int index = 0;
		for (TradeTickerSpec ticker : tickers) {
			if(index == results.size()) {
				index = 0;
			}
			results.get(index).add(ticker);
			index++;
		}
		return results;
		
	}
	
	

}

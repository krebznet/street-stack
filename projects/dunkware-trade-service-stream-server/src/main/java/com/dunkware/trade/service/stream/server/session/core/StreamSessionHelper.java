package com.dunkware.trade.service.stream.server.session.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

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
	
	
	public static List<TradeTickerSpec> searchForDups(List<List<TradeTickerSpec>> lists) { 
		List<TradeTickerSpec> dupes = new ArrayList<TradeTickerSpec>();
		Map<String,TradeTickerSpec> check = new ConcurrentHashMap<String,TradeTickerSpec>();
		for (List<TradeTickerSpec> list : lists) {
			for (TradeTickerSpec tick : list) {
				if(check.get(tick.getSymbol()) != null) { 
					dupes.add(tick);
				}
				check.put(tick.getSymbol(), tick);
			}
		}
		return dupes;
	}
	
	

}

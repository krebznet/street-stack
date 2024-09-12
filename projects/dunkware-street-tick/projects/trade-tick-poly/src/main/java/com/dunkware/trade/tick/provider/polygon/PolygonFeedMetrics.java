package com.dunkware.trade.tick.provider.polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonQuote;

public class PolygonFeedMetrics {
	
	private Map<String,MetricsTicker> tickers = new ConcurrentHashMap<String,MetricsTicker>();
	private AtomicInteger quotes = new AtomicInteger();
	private AtomicInteger aggs = new AtomicInteger();
	private String name; 
	private PolygonFeed feed;
	private List<String> invalidSymbols = new ArrayList<String>();
	
	private int qps = -1;
	private int agps = -1;
	private int mps = -1;
	
	public PolygonFeedMetrics(PolygonFeed feed) { 
		this.feed = feed;
		
	}
	public void tickerAdded(PolygonTicker ticker) { 
		tickers.put(ticker.getSymbol(), new MetricsTicker(ticker.getSymbol()));
	}
	
	public void aggEvent(PolygonAggEvent event) { 
		tickers.get(event.getSymbol()).aggs.incrementAndGet();
		aggs.incrementAndGet();
		
	}
	
	public void resetDay() { 
		quotes.set(0);
		aggs.set(0);
		qps = -1;
		agps = -1;
		mps = -1;
		for (String key : tickers.keySet()) {
			tickers.get(key).reset();
		}
	}
	
	public void quoteEvent(PolygonQuote quote) {
		tickers.get(quote.getSymbol()).quotes.incrementAndGet();
		quotes.incrementAndGet();
	}
	
	public void invalidSymbol(String symbol) { 
		this.invalidSymbols.add(symbol);
	}
	
	public List<String> getInvalidSymbols() { 
		return invalidSymbols;
	}
	
	public int getQuoteCount() { 
		return quotes.get();
	}
	
	public int getAggCount() {
		return aggs.get();
	}
	
	public int getMps() { 
		return mps;
	}
	
	public int getQps() { 
		return qps;
	}
	
	public int getAgps() { 
		return agps;
	}
	
	
	public int getMessageCount() { 
		return getAggCount() + getQuoteCount();
	}
	
	public MetricsTicker getTickerMetrics(String symbol) { 
		return this.tickers.get(symbol);
	}
	
	public void secondUpdate() { 
		if(qps == -1) {
			qps = quotes.get();
		} else { 
			qps = quotes.get() - qps;
		}
		if(agps == -1 ) { 
			agps = aggs.get();
		} else { 
			agps = aggs.get() - agps;
		}
		if(mps == -1) { 
			mps = aggs.get() + quotes.get();
		} else { 
			mps = (aggs.get() + quotes.get()) - mps;
		}
	}
	
	public class MetricsTicker {
		
		public MetricsTicker(String symbol) { 
			this.symbol = symbol;
		}
		
		public String symbol;
		public AtomicInteger quotes = new AtomicInteger(0); 
		public AtomicInteger aggs = new AtomicInteger(0);
		
		public void reset() { 
			quotes.set(0);
			aggs.set(0);
		}
		
	}
}

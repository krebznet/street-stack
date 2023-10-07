package com.dunkware.trade.tick.provider.polygon.tests;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.provider.polygon.PolygonFeed;
import com.dunkware.trade.tick.provider.polygon.PolygonTicker;

public class PolygonTest1 {
	
	public static final String APIKEY = "n95x4f7AK_Am6AZ9qNSxcn9u4obpKsMA";
	public static final boolean printTickers = false; 
	public static void main(String[] args) {
		new PolygonTest1();
	}
	
	private PolygonFeed feed = null;
	
	private DExecutor executor; 
	public PolygonTest1() { 
		executor = new DExecutor(15);
		List<String> symbols = new ArrayList<String>();
		symbols.add("AAPL");
		try {
			feed = new PolygonFeed();
			feed.connect(APIKEY,symbols,executor);
		} catch (Exception e) {
			System.err.println("Feed connect errror " + e.toString());
			e.printStackTrace();
		}
		Thread runner = new Thread() { 
			
			public void run() { 
				try {
					
					while(true) { 
						Thread.sleep(1000);
						if(!printTickers) { 
							continue;
						}
						for (PolygonTicker ticker : feed.getTickers()) {
							System.out.println("ticker:" + ticker.getSymbol() + "price=" + ticker.getLastPrice() + "volume=" + ticker.getVolume());
						}	
					}
					
				} catch (Exception e) {
					
				}
			}
		};
		runner.start();
	}

}

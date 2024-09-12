package com.dunkware.trade.tick.provider.atick.design;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class StreamDesign {
	
	// so this is going to have to have a queue of message consumers; 
	private Map<String,StreamSymbol> symbols = new ConcurrentHashMap<String,StreamSymbol>();
	private BlockingQueue<Object> streamQueue = new LinkedBlockingQueue<Object>();
	
	
	public void design() { 
		
	}
	
	public void subscribe(String symbol) { 
		// going to have to make a snapshot request. 
		// populate all the data on the symbol and then put it in there 
		// then we will have to subscribe to it using the fucking API; 
		
	}
	private class MessageConsumer extends Thread {
		
		// you should have active tick be its own fuckin server fuck you 
		public void run() { 
			while(!interrupted()) { 
				try {
					Object message = streamQueue.take();
					StreamSymbol symbol = symbols.get("symbol");
					// update some metrics increment quote + trade messages; 
					// if trade -- set last tradeTime, lastPrice, lastSize 
				} catch (Exception e) {
					
				}
			}
		}
	}

}

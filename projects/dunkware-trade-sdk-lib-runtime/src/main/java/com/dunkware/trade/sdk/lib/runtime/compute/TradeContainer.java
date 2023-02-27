package com.dunkware.trade.sdk.lib.runtime.compute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.sdk.core.runtime.trade.Trade;

public class TradeContainer {
	
	private List<Trade> trades = new ArrayList<Trade>();
	private Semaphore tradeLock = new Semaphore(1);
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void insert(Trade trade) { 
		try {
			tradeLock.acquire();
			trades.add(trade);
		} catch (Exception e) {
			
		} finally { 
			tradeLock.release();
		}
	}
	
	public void remove(Trade trade) { 
		try {
			tradeLock.acquire();
			trades.remove(trade);
		} catch (Exception e) {
			// TODO: handle exception
			
		} finally { 
			tradeLock.release();
		}
	}
	
	public int getOpenTradeCount() { 
		try {
			tradeLock.acquire();
			return trades.size();
		} catch (Exception e) {
			logger.error("Exception getting trade count " + e.toString());
			return -1;
		} finally { 
			tradeLock.release();
			
		}
	}
	
	public int getOpenTradeCount(String symbol) { 
		try {
			tradeLock.acquire();
			int count = 0;
			for (Trade trade : trades) {
				if(trade.getSymbol().equalsIgnoreCase(symbol)) { 
					count++;
				}
			}
			return count;
		} catch (Exception e) {
			logger.error("Exception getting trade count " + e.toString());
			return 0;
		} finally { 
			tradeLock.release();
			
		}
	}
	

}

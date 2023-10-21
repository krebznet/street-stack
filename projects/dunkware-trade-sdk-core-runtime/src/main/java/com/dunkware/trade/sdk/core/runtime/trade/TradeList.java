package com.dunkware.trade.sdk.core.runtime.trade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.sdk.core.model.trade.TradeStatus;

public class TradeList {
	
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
	
	public double getActiveCapital() { 
		return 3;
		
	}
	
	public double getTradedCapital() { 
		return 5;
	}
	
	public int getActiveTradeCount() { 
		try {
			tradeLock.acquire();
			int count = 0; 
			for (Trade trade : trades) {
				if(trade.getSpec().getStatus() == TradeStatus.Open || trade.getSpec().getStatus() == TradeStatus.Opening
						|| trade.getSpec().getStatus() == TradeStatus.Closing) { 
					count++;
				}
			}
			return count;
		} catch (Exception e) {
			logger.error("Exception getting trade count " + e.toString());
			return -1;
		} finally { 
			tradeLock.release();
			
		}
	}
	
	public int getOpenTradeCount() { 
		try {
			tradeLock.acquire();
			int count = 0; 
			for (Trade trade : trades) {
				if(trade.getSpec().getStatus() == TradeStatus.Open) { 
					count++;
				}
			}
			return count;
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

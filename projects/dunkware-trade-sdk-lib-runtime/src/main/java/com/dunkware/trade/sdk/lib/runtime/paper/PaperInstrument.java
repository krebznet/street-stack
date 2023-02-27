package com.dunkware.trade.sdk.lib.runtime.paper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.api.instrument.InstrumentListener;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class PaperInstrument implements Instrument {

	private TradeTickerSpec tickerSpec; 
	private List<InstrumentListener> listeners = new ArrayList<InstrumentListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private double last;
	private LocalDateTime lastUpdate = null;
	
	private double seed; 
	private double increment; 
	
	private Updater updater; 
	
	public PaperInstrument(TradeTickerSpec tickerSpec) { 
		this.tickerSpec = tickerSpec;
		seed = DRandom.getRandom(2, 300);
		last = seed; 
		lastUpdate = LocalDateTime.now();
		increment = DRandom.getRandom(-1, 2);
		updater = new Updater();
		updater.start();
	}
	
	public void dispose() { 
		updater.interrupt();
	}
	
	
	@Override
	public void setLast(double last) {
		
	}

	@Override
	public double getLast() {
		return last;
	}

	@Override
	public void setAskPrice(double askPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getAskPrice() {
		return last; 
	}

	@Override
	public void setAskSize(int askSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAskSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBidSize(int bidSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBidSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBidPrice() {
		return last;
	}

	@Override
	public void setBidPrice(double bidPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOpen(double open) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getOpen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setClose(double close) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getClose() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHigh(double high) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHigh() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLow(double low) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVolume(int volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTradeCount(int tradeCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTradeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TradeTickerSpec getTicker() {
		return tickerSpec;
	}

	@Override
	public LocalDateTime getLastUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(InstrumentListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
		
	}

	@Override
	public void removeListener(InstrumentListener listener) {
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}	
	}
	
	private class Updater extends Thread { 
		
		public void run() { 
			try {
				Thread.sleep(3000); 
				// increment 1 percent. 
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}

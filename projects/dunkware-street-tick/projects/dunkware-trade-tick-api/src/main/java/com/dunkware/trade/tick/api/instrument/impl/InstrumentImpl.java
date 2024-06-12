package com.dunkware.trade.tick.api.instrument.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.api.instrument.InstrumentListener;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.utils.core.concurrent.DunkExecutor;

public class InstrumentImpl implements Instrument {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<InstrumentListener> listeners = new ArrayList<InstrumentListener>();
	private Semaphore listenerLock = new Semaphore(1);

	private volatile double last = Double.MIN_VALUE;
	private volatile double askPrice = Double.MIN_VALUE;
	private volatile double bidPrice = Double.MIN_VALUE;
	private volatile int askSize = Integer.MIN_VALUE;
	private volatile int bidSize = Integer.MIN_VALUE;
	private volatile double high = Double.MIN_VALUE;
	private volatile double low = Double.MIN_VALUE;
	private volatile double open = Double.MIN_VALUE;
	private volatile double close = Double.MIN_VALUE;
	private volatile int tradeCount = Integer.MIN_VALUE;
	private volatile int volume = Integer.MIN_VALUE;
	
	private TradeTickerSpec ticker;
	private DunkExecutor executor;

	private volatile LocalDateTime lastUpdate = null;

	public InstrumentImpl(TradeTickerSpec ticker, DunkExecutor executor) {
		this.ticker = ticker;
		this.executor = executor;
	}

	@Override
	public void setLast(double last) {
		this.last = last;
		notifyUpdate();
	}

	@Override
	public double getLast() {
		return last;
	}

	@Override
	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
		notifyUpdate();
	}

	@Override
	public double getAskPrice() {
		return askPrice;
	}

	@Override
	public void setAskSize(int askSize) {
		this.askSize = askSize;
		notifyUpdate();
	}

	@Override
	public int getAskSize() {
		return askSize;
	}

	@Override
	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
		notifyUpdate();
	}

	@Override
	public int getBidSize() {
		return bidSize;
	}

	
	@Override
	public double getBidPrice() {
		return bidPrice;
	}

	@Override
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
		notifyUpdate();
	}
	
	

	@Override
	public void setOpen(double open) {
		this.open = open;
		notifyUpdate();
	}

	@Override
	public double getOpen() {
		return open;
	}

	@Override
	public void setClose(double close) {
		this.close = close; 
		notifyUpdate();
	}

	@Override
	public double getClose() {
		return close; 
	}

	@Override
	public void setHigh(double high) {
		this.high = high;
		notifyUpdate();
	}

	@Override
	public double getHigh() {
		return high; 
	}

	@Override
	public void setLow(double low) {
		this.low = low;
		notifyUpdate();
	}

	@Override
	public double getLow() {
		return low;
	}
	
	

	@Override
	public void setVolume(int volume) {
		this.volume = volume;
		notifyUpdate();
	}

	@Override
	public int getVolume() {
		return volume;
	}

	@Override
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
		notifyUpdate();
	}

	@Override
	public int getTradeCount() {
		return tradeCount;
	}

	@Override
	public TradeTickerSpec getTicker() {
		return ticker;
	}

	@Override
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public void addListener(InstrumentListener listener) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					listenerLock.acquire();
					listeners.add(listener);
				} catch (Exception e) {

				} finally {
					listenerLock.release();
				}
			}
		};
		executor.execute(runner);
	}

	@Override
	public void removeListener(final InstrumentListener listener) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					listenerLock.acquire();
					listeners.remove(listener);
				} catch (Exception e) {

				} finally {
					listenerLock.release();
				}
			}
		};
		executor.execute(runner);
	}

	private void notifyUpdate() {
		lastUpdate = LocalDateTime.now();
		Updater updater = new Updater();
		executor.execute(updater);
	}

	private class Updater implements Runnable {

		@Override
		public void run() {
			try {
				listenerLock.acquire();
				for (InstrumentListener listener : listeners) {
					try {
						listener.instrumentUpdate(InstrumentImpl.this);
					} catch (Exception e) {
						logger.error("Fatal Instrument Listener Unhandled Exception " + listener.getClass().getName() + " " + e.toString(),e);
					}
				}
			} catch (Exception e) {
		
			} finally { 
				listenerLock.release();
			}
		}

	}
}

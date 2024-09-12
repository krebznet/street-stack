package com.dunkware.trade.tick.api.instrument.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.api.instrument.InstrumentProvider;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public abstract class InstrumentProviderImpl implements InstrumentProvider  {

	private ConcurrentHashMap<TradeTickerSpec, InstrumentSubscription> subscriptions = new ConcurrentHashMap<TradeTickerSpec, InstrumentSubscription>();
	
	@Override
	public Instrument acquireInstrument(TradeTickerSpec ticker) throws Exception {
		if(subscriptions.containsKey(ticker)) { 
			InstrumentSubscription sub = subscriptions.get(ticker);
			sub.subscribe();
			return sub.getInstrument();
		}
		Instrument inst = subscribe(ticker);
		InstrumentSubscription sub = new InstrumentSubscription(inst);
		sub.subscribe();
		subscriptions.put(ticker, sub);
		return inst;
	}

	@Override
	public void releaseInstrument(Instrument instrument) {
		if(!subscriptions.containsKey(instrument.getTicker())) { 
			// weird okay whatever
			return;
		}
		InstrumentSubscription sub = subscriptions.get(instrument.getTicker());
		sub.unsubscribe();
		if(sub.getSubscriptionCount() == 0) { 
			unsubscribe(instrument);
			subscriptions.remove(instrument.getTicker());
		}
	}
	
	protected abstract Instrument subscribe(TradeTickerSpec ticker) throws Exception; 
	
	protected abstract void unsubscribe(Instrument instrument) ;
	
	private class InstrumentSubscription { 
		
		private Instrument instrument; 
		private AtomicInteger subscriptions = new AtomicInteger(0);
		
		public InstrumentSubscription(Instrument instrument) { 
			this.instrument = instrument;
		}
		
		public void subscribe() { 
			subscriptions.incrementAndGet();
		}
		
		public void unsubscribe() { 
			subscriptions.decrementAndGet();
		}
		
		public int getSubscriptionCount() { 
			return subscriptions.get();
		}
		
		public Instrument getInstrument() { 
			return instrument;
		}
	}

}

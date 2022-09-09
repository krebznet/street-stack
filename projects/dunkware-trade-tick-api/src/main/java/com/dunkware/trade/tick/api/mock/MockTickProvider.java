package com.dunkware.trade.tick.api.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.provider.ATickProvider;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.model.feed.TickFeedSnapshot;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@ATickProvider(type="Mock")
public class MockTickProvider implements TickProvider {
	
	private Map<String,TickFeedSubscription> subscriptions = new ConcurrentHashMap<String,TickFeedSubscription>();
	private Map<String,Integer> seeds = new ConcurrentHashMap<String,Integer>();
	private DExecutor executor; 
	private TickFeed feed; 
	private TickProviderSpec spec;
	private int interval;
	private SnapshotUpdater updater;
	
 	@Override
	public void connect(TickProviderSpec providerSpec, TickFeed feed, DExecutor executor) throws TickProviderException {
		this.feed = feed;
		this.spec =providerSpec;
		this.executor = executor;
		interval = (Integer)providerSpec.getProperties().get("interval");
		updater = new SnapshotUpdater();
		updater.start();
		
	}

	@Override
	public void subscribeTickers(Collection<TradeTickerSpec> tickers) throws Exception {
		for (TradeTickerSpec tradeTickerSpec : tickers) {
			if(subscriptions.containsKey(tradeTickerSpec.getSymbol()) == false) { 
				// get a random seed between 1 and 200 
				Integer seed = DRandom.getRandom(1, 10);
				TickFeedSnapshot snap = new TickFeedSnapshot();
				snap.setLast(seed);
				snap.setVolume(seed);
				snap.setAskPrice(seed);
				snap.setBidPrice(seed);
				snap.setBidSize(seed);
				snap.setTradeCount(seed);
				snap.setAskSize(seed);
				snap.setTime(DDateTime.now());
				snap.setSymbol(tradeTickerSpec.getSymbol());
				seeds.put(tradeTickerSpec.getSymbol(), seed);
				TickFeedSubscription sub = new TickFeedSubscription(snap, tradeTickerSpec);
				subscriptions.put(tradeTickerSpec.getSymbol(),sub);
			}
		}
	}

	@Override
	public boolean isSubscribed(TradeTickerSpec ticker) {
		if(subscriptions.containsKey(ticker.getSymbol()) == false) { 
			return false;
		}
		return true; 
	}

	@Override
	public boolean isSubscribed(String symbol) {
		if(subscriptions.containsKey(symbol) == true) { 
			return true; 
		}
		return false; 
	}

	@Override
	public TickFeedSubscription getSubscription(TradeTickerSpec ticker) throws TickProviderException {
		return subscriptions.get(ticker.getSymbol());
	}

	@Override
	public TickFeedSubscription getSubscription(String symbol) throws TickProviderException {
		return subscriptions.get(symbol);
	}

	@Override
	public TickProviderState getState() {
		return TickProviderState.CONNECTED;
	}

	@Override
	public TickProviderStatsSpec getStats() {
		TickProviderStatsSpec spec = new TickProviderStatsSpec();
		spec.setInvalidSubscriptionCount(0);
		spec.setLastSnapshotMessage("dd");
		spec.setMessageCount(3);
		return spec;
	}

	@Override
	public String getConnectionError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TradeTickerSpec> getInvalidTickers() {
		return new ArrayList<TradeTickerSpec>();
	}

	@Override
	public Collection<TickFeedSubscription> getSubscriptions() {
		return subscriptions.values();
	}

	@Override
	public int getQuoteCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTradeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSnapshotCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newDay() {
		// TODO Auto-generated method stub
		
	}
	
	
	private class SnapshotUpdater extends Thread { 
		
		public void run() { 
			try {
				while(true) { 
					Thread.sleep(interval* 1000);
					for (String test : subscriptions.keySet()) {
						TickFeedSubscription sub = subscriptions.get(test);
						int seed = seeds.get(sub.getSymbol());
						seed++;
						TickFeedSnapshot snap = new TickFeedSnapshot();
						snap.setLast(seed);
						snap.setVolume(seed);
						snap.setAskPrice(seed);
						snap.setBidPrice(seed);
						snap.setBidSize(seed);
						snap.setTradeCount(seed);
						snap.setAskSize(seed);
						snap.setTime(DDateTime.now());
						
						seeds.put(test, seed);
						sub.setLastSnapshot(snap);
						
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	

}

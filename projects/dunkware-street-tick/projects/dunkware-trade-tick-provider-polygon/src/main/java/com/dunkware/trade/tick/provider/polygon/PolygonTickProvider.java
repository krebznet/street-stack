package com.dunkware.trade.tick.provider.polygon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.provider.ATickProvider;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@ATickProvider(type = "Polygon")
public class PolygonTickProvider implements TickProvider {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String apikey;
	
	private PolygonFeed feed; 
	
	
	private DExecutor executor; 
	
	TickProviderStatsSpec stats;
	
	private TickSnapshotFactory tickFactory;
	
	private TickProviderState state = TickProviderState.CONNECTED;
	
	private DDateTime startTime; 
	
	private Map<String,TradeTickerSpec> tickerSubscriptions = new ConcurrentHashMap<String,TradeTickerSpec>();

	
	@Override
	public void connect(TickProviderSpec providerSpec, TickFeed feed, DExecutor executor) throws TickProviderException {
		// TODO Auto-generated method stub
		
	}

	


	@Override
	public boolean isSubscribed(String symbol) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public boolean isSubscribed(TradeTickerSpec ticker) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public TickFeedSubscription getSubscription(TradeTickerSpec ticker) throws TickProviderException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TickFeedSubscription getSubscription(String symbol) throws TickProviderException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getConnectionError() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<TradeTickerSpec> getInvalidTickers() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Collection<TickFeedSubscription> getSubscriptions() {
		// TODO Auto-generated method stub
		return null;
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


	/*
	 * @Override public void connect(TickProviderSpec type, TradeSymbolService
	 * idProvider, DExecutor executor) throws TickProviderException { this.apikey =
	 * type.getProperties().get("apiKey").toString(); stats = new
	 * TickProviderStatsSpec(); stats.setName("Polygon"); feed = new PolygonFeed();
	 * try { List<String> symbols = new ArrayList<String>();
	 * feed.connect(this.apikey,symbols,executor);
	 * 
	 * } catch (Exception e) { throw new
	 * TickProviderException("Exception connecting Polygon feed " + e.toString()); }
	 * 
	 * startTime = DDateTime.now(DTimeZone.NewYork); tickFactory = new
	 * TickSnapshotFactory(); tickFactory.start(); }
	 */
	

	@Override
	public void subscribeTickers(Collection<TradeTickerSpec> tickers) {
		List<String> symbols = new ArrayList<String>();
		for (TradeTickerSpec spec : tickers) {
			if(tickerSubscriptions.get(spec.getSymbol()) == null) {
				tickerSubscriptions.put(spec.getSymbol(), spec);
				symbols.add(spec.getSymbol());
			}
		}
		feed.subscribe(symbols);
	}

	

	@Override
	public TickProviderStatsSpec getStats() {
		TickProviderStatsSpec spec = new TickProviderStatsSpec();
		spec.setInvalidSubscriptionCount(feed.getMetrics().getInvalidSymbols().size());
		spec.setMessageCount(feed.getMetrics().getMessageCount());
		spec.setQuoteCount(feed.getMetrics().getQuoteCount());
		spec.setSecondAggCount(feed.getMetrics().getAggCount());
		spec.setMps(feed.getMetrics().getMps());
		spec.setQps(feed.getMetrics().getQps());
		spec.setAps(feed.getMetrics().getAgps());
		spec.setName("Polygon");
		spec.setStartTime(startTime);
		spec.setState(state);
		spec.setMessageQueueSize(feed.getMessageQueueSize());
		return spec;
	}
	

	@Override
	public TickProviderState getState() {
		return state;
	}

	


	
	
	/*
	 * @Override public List<TradeTickerSpec> getValidatedSubscriptions() {
	 * List<String> validSymbols = feed.getValidatedSymbols(); List<TradeTickerSpec>
	 * results = new ArrayList<TradeTickerSpec>(); for (String symbol :
	 * validSymbols) { results.add(this.tickerSubscriptions.get(symbol)); } return
	 * results; }
	 * 
	 * 
	 * @Override public TickFeedTicker getFeedTicker(String symbol) { PolygonTicker
	 * ticker = feed.getTicker(symbol); if(ticker == null) { return null;
	 * 
	 * } return ticker.getFeedTicker(); }
	 */



	private class TickSnapshotFactory extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					for (PolygonTicker ticker : feed.getTickers()) {
						if(ticker.isValidated() == false) { 
							continue;
						}
						List<Tick.TickField> fields = new ArrayList<Tick.TickField>();
						fields.add(TickField.newBuilder().setId(TradeTicks.FieldSymbol).setStringValue(ticker.getSymbol()).setType(TickFieldType.STRING).build());
						if(ticker.getLastPrice() != -1) {
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotLastPrice).setDoubleValue(ticker.getLastPrice()).setType(TickFieldType.DOUBLE).build());							
						}
						if(ticker.getVolume() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotVolume).setIntValue(ticker.getVolume()).setType(TickFieldType.INT).build());
						}
						if(ticker.getTradeCount().get() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotTradeCount).setIntValue(ticker.getTradeCount().get()).setType(TickFieldType.DOUBLE).build());
						}
						if(ticker.getAskPrice() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotAskPrice).setDoubleValue(ticker.getAskPrice()).setType(TickFieldType.DOUBLE).build());
						}
						if(ticker.getBidPrice() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotBidPrice).setDoubleValue(ticker.getBidPrice()).setType(TickFieldType.DOUBLE).build());
						}
						if(ticker.getBidSize() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotBidPrice).setIntValue(ticker.getBidSize()).setType(TickFieldType.DOUBLE).build());
						}
						if(ticker.getAskSize() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldSnapshotAskSize).setIntValue(ticker.getAskSize()).setType(TickFieldType.INT).build());
						}
						Tick tick = Tick.newBuilder().setType(TradeTicks.TickSnapshot).addAllTickFields(fields).setType(TickFieldType.INT_VALUE).build();
						//streamTick(tick);
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) { 
					return;
				}
				logger.error("Exception sending tick on stream " + e.toString());
			}
		}
	}





	


	
	
	
	
}

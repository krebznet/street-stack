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
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.provider.ATickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.api.provider.TradeSymbolService;
import com.dunkware.trade.tick.api.provider.impl.TickProviderImpl;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.feed.TickFeedTicker;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@ATickProvider(type = "Polygon")
public class PolygonTickProvider extends TickProviderImpl {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String apikey;
	
	private PolygonFeed feed; 
	
	private TradeSymbolService idProvider; 
	
	private DExecutor executor; 
	
	TickProviderStatsSpec stats;
	
	private TickSnapshotFactory tickFactory;
	
	private TickProviderState state = TickProviderState.CONNECTED;
	
	private DDateTime startTime; 
	
	private Map<String,TradeTickerSpec> tickerSubscriptions = new ConcurrentHashMap<String,TradeTickerSpec>();

	@Override
	public void connect(TickProviderSpec type, TradeSymbolService idProvider, DExecutor executor) throws TickProviderException {
		this.apikey = type.getProperties().get("apikey").toString();
		stats = new TickProviderStatsSpec();
		stats.setName("Polygon");
		
		try {
			feed.connect(apikey, null, executor);
			
		} catch (Exception e) {
			throw new TickProviderException("Exception connecting Polygon feed " + e.toString());
		}
		
		startTime = DDateTime.now(DTimeZone.NewYork);
		tickFactory = new TickSnapshotFactory();
		tickFactory.start();
	}
	
	

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
	public String tickerToString(TradeTickerSpec ticker) {
		return ticker.toString();
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

	


	@Override
	protected void subscribe(String symbol) throws TickProviderException {
		// okay 
	}

	@Override
	protected void unsubscribe(String symbol) {
		
	}
	
	
	
	@Override
	public List<TradeTickerSpec> getValidatedSubscriptions() {
		List<String> validSymbols = feed.getValidatedSymbols();
		List<TradeTickerSpec> results = new ArrayList<TradeTickerSpec>();
		for (String symbol : validSymbols) {
			results.add(this.tickerSubscriptions.get(symbol));
		}
		return results;
	}
	

	@Override
	public TickFeedTicker getFeedTicker(String symbol) {
		PolygonTicker ticker = feed.getTicker(symbol);
		if(ticker == null) { 
			TickFeedTicker feedTicker = new TickFeedTicker();
			feedTicker.setSymbol(symbol);
			feedTicker.setLastPrice(-1);
			return feedTicker;
		}
		TickFeedTicker feedTicker = new TickFeedTicker();
		feedTicker.setSymbol(ticker.getSymbol());
		feedTicker.setLastPrice(ticker.getLastPrice());
		feedTicker.setVolume(ticker.getVolume());
		feedTicker.setTradeCount(ticker.getTradeCount().get());
		return feedTicker;
	}




	private class TickSnapshotFactory extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					for (PolygonTicker ticker : feed.getTickers()) {
						List<Tick.TickField> fields = new ArrayList<Tick.TickField>();
						fields.add(TickField.newBuilder().setId(TradeTicks.FieldSymbol).setStringValue(ticker.getSymbol()).build());
						if(ticker.getLastPrice() != -1) {
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldLastPrice).setDoubleValue(ticker.getLastPrice()).build());							
						}
						if(ticker.getVolume() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldVolume).setLongValue(ticker.getVolume()).build());
						}
						if(ticker.getTradeCount().get() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldTradeCount).setIntValue(ticker.getTradeCount().get()).build());
						}
						if(ticker.getAskPrice() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldAskPrice).setDoubleValue(ticker.getAskPrice()).build());
						}
						if(ticker.getBidPrice() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldBidPrice).setDoubleValue(ticker.getBidPrice()).build());
						}
						if(ticker.getBidSize() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldBidSize).setIntValue(ticker.getBidSize()).build());
						}
						if(ticker.getAskSize() != -1) { 
							fields.add(TickField.newBuilder().setId(TradeTicks.FieldAskSize).setIntValue(ticker.getAskSize()).build());
						}
						Tick tick = Tick.newBuilder().setType(TradeTicks.TickSnapshot).addAllTickFields(fields).build();
						streamTick(tick);
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


	@Override
	public void resetDay() {
		feed.resetDay();
	}



	@Override
	public List<TradeTickerSpec> getInValidatedSubscriptions() {
		List<String> validSymbols = feed.getInValidatedSymbols();
		List<TradeTickerSpec> results = new ArrayList<TradeTickerSpec>();
		for (String symbol : validSymbols) {
			results.add(this.tickerSubscriptions.get(symbol));
		}
		return results;
	}
	
	
}

package com.dunkware.trade.tick.provider.polygon;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.provider.ATickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.api.provider.TradeSymbolService;
import com.dunkware.trade.tick.api.provider.impl.TickProviderImpl;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.feed.TickFeedTicker;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
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
		tickFactory = new TickSnapshotFactory();
		tickFactory.start();
	}
	
	

	@Override
	public void subscribeTickers(List<String> tickers) {
		this.feed.subscribe(tickers);
	}

	@Override
	public String tickerToString(TradeTickerSpec ticker) {
		return ticker.toString();
	}

	@Override
	public TickProviderStatsSpec getProviderStats() {
		return stats;
	}

	@Override
	protected void subscribe(String symbol) throws TickProviderException {
		// okay 
	}

	@Override
	protected void unsubscribe(String symbol) {
		
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
	
	
}

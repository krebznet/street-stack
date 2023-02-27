package com.dunkware.trade.service.beach.server.runtime.core.bot;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.lib.runtime.compute.TradeContainer;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachService;
import com.dunkware.trade.service.beach.server.runtime.BeachStream;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.dunkware.xstream.model.signal.StreamSignal;
import com.dunkware.xstream.model.signal.StreamSignalListener;

import comm.dunkware.trade.service.beach.web.bot.WebBotPlay;
import comm.dunkware.trade.service.beach.web.bot.WebBotTradeSide;

public class BeachBotPlay implements StreamSignalListener  {
	
	@Autowired
	private BeachService beachService; 
	
	private WebBotPlay model;
	private BeachBot bot;
	
	private EntryType entryType; 
	private ExitType exitType; 
	
	
	private TradeContainer trades = new TradeContainer();
	
	private ConcurrentHashMap<String, LocalTime> lastSymbolTradeTimestamps = new ConcurrentHashMap<String, LocalTime>();
	
	private BeachStream signalStream; 
	
	public void init(WebBotPlay play, BeachBot bot) throws Exception { 
		this.model = play; 
		this.bot = bot; 
		
		entryType = BeachBotHelper.toEntryType(play.getEntryStrategy());
		exitType = BeachBotHelper.toExitType(play.getExitStrategy());
		
		
		play.getSignal();
		play.getStream();
		
		try {
			signalStream = beachService.getStream(play.getStream());
		} catch (Exception e) {
			throw new Exception("Trade Bot Play Signal Not Found Play " + play.getName());
		}
		// add a signal listener. 
		signalStream.addSignalListener(this, play.getSignal());
		
		
		// todo signal listener. 
		play.getSignal();

		
	}
	

	@Override
	public void onSignal(StreamSignal signal) {
		if(canTrade(signal)) { 
			TradeType type = createTradeType(signal);
			try {
			Trade trade = bot.createPlayTrade(this, type);
			trades.insert(trade);
			
			trade.open();
			} catch (Exception e) {
				// trade create error logging / handling 
			}
			
			
		}
	}
	
	private TradeType createTradeType(StreamSignal signal) { 
		
		TradeType type = new TradeType();
		type.setCapital(model.getCapital().doubleValue());
		type.setEntry(entryType);
		type.setExit(exitType);
		if(model.getTradeSide() == WebBotTradeSide.Long) { 
			type.setSide(TradeSide.LONG);
		} else { 
			type.setSide(TradeSide.SHORT);
		}
		type.setName(model.getName());
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setSymbol(signal.getEntIdent());
		ticker.setType(TradeTickerType.Equity);
		type.setTicker(ticker);
		
		return type; 
	}
	
	private boolean canTrade(StreamSignal signal) { 
		if(model.getActiveTradeLimit() != null) { 
			if(trades.getOpenTradeCount() == model.getActiveTradeLimit().intValue() || trades.getOpenTradeCount() > model.getActiveTradeLimit().intValue()) {
				return false; 
				// log
			}	
		}
		
		if(model.getActiveSymbolLimit() != null) { 
			int symbolOpenTradeCount = trades.getOpenTradeCount(signal.getEntIdent());
			if(symbolOpenTradeCount == model.getActiveSymbolLimit().intValue()) { 
				return false; 
				// log 
			}
		}
		
		return true; 
		

	}



	

}

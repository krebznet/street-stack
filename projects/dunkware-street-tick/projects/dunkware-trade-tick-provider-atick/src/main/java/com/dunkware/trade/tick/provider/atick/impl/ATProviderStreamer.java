package com.dunkware.trade.tick.provider.atick.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.util.helpers.DConverter;
import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;
import com.dunkware.trade.tick.provider.atick.ActiveTickProvider;

import at.feedapi.ActiveTickStreamListener;
import at.shared.ATServerAPIDefines.ATQUOTESTREAM_QUOTE_UPDATE;
import at.shared.ATServerAPIDefines.ATQUOTESTREAM_REFRESH_UPDATE;
import at.shared.ATServerAPIDefines.ATQUOTESTREAM_TRADE_UPDATE;

public class ATProviderStreamer extends ActiveTickStreamListener {

	private ATProviderSession session; 
	private ActiveTickProvider provider;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ATProviderStreamer(ATProviderSession session, ActiveTickProvider provider) {
		super(session.GetSession(),false);
		this.provider = provider;
		this.session = session;
		
	}

	@Override
	public void OnATStreamQuoteUpdate(ATQUOTESTREAM_QUOTE_UPDATE update) {
		String strSymbol = new String(update.symbol.symbol);
		
		int plainSymbolIndex = strSymbol.indexOf((byte) 0);
		strSymbol = strSymbol.substring(0, plainSymbolIndex);
		provider.quoteUpdate(strSymbol);
		TickFeedQuote quote = new TickFeedQuote();
		quote.setSymbol(strSymbol);
		quote.setAskPrice(update.askPrice.price);
		quote.setBidPrice(update.bidPrice.price);
		quote.setBidSize(DConverter.longToInt(update.bidSize));
		quote.setAskSize(DConverter.longToInt(update.askSize));
		if(strSymbol.equals("SPY")) { 
			logger.info(MarkerFactory.getMarker("SPYQ"),"AP{} AS{} BP{} BS{}", quote.getAskPrice(), quote.getAskSize(), quote.getBidPrice(), quote.getBidSize());
		}
		//TODO: time 
		provider.onQuote(quote);
		
	}

	@Override
	public void OnATStreamRefreshUpdate(ATQUOTESTREAM_REFRESH_UPDATE update) {
		
	}

	@Override
	public void OnATStreamTradeUpdate(ATQUOTESTREAM_TRADE_UPDATE update) {
		String strSymbol = new String(update.symbol.symbol);
		
		int plainSymbolIndex = strSymbol.indexOf((byte) 0);
		strSymbol = strSymbol.substring(0, plainSymbolIndex);
		provider.tradeUpdate(strSymbol);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, update.lastDateTime.hour);
		cal.set(Calendar.MINUTE, update.lastDateTime.minute);
		cal.set(Calendar.SECOND, update.lastDateTime.second);
		cal.set(Calendar.MILLISECOND,update.lastDateTime.milliseconds);
		long lastDateTime = cal.getTimeInMillis();
		TickFeedTrade trade = new TickFeedTrade();
		trade.setSymbol(strSymbol);
		trade.setSize(DConverter.longToInt(update.lastSize));
		trade.setPrice(update.lastPrice.price);
		// okay we can get it here; 
		List<Tick.TickField> fields = new ArrayList<Tick.TickField>();
//		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldLastTradeDateTime).setType(TickFieldType.LONG).setLongValue(lastDateTime).build());
		if(strSymbol.equals("SPY")) { 
			logger.info(MarkerFactory.getMarker("SPYT"),"SIZE{} PRICE{}", trade.getSize(), trade.getPrice());
		}
		provider.onTrade(trade);
	}
	
	

}

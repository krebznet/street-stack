package com.dunkware.trade.tick.provider.atick.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.provider.atick.ActiveTickProvider;

import at.feedapi.ActiveTickStreamListener;
import at.shared.ATServerAPIDefines.ATQUOTESTREAM_QUOTE_UPDATE;
import at.shared.ATServerAPIDefines.ATQUOTESTREAM_REFRESH_UPDATE;
import at.shared.ATServerAPIDefines.ATQUOTESTREAM_TRADE_UPDATE;

public class ATProviderStreamer extends ActiveTickStreamListener {

	private ATProviderSession session; 
	private ActiveTickProvider provider;
	
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

		List<Tick.TickField> fields = new ArrayList<Tick.TickField>();
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldSymbol).setType(TickFieldType.STRING).setStringValue(strSymbol).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAskSize).setType(TickFieldType.LONG).setLongValue(update.askSize).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldBidSize).setType(TickFieldType.LONG).setLongValue(update.bidSize).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldBidPrice).setType(TickFieldType.DOUBLE).setDoubleValue(update.bidPrice.price).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldAskPrice).setType(TickFieldType.DOUBLE).setDoubleValue(update.askPrice.price).build());
	
		Tick tick = Tick.newBuilder().setType(TradeTicks.TickQuote).addAllTickFields(fields).build();
		provider.streamTick(tick);
	}

	@Override
	public void OnATStreamRefreshUpdate(ATQUOTESTREAM_REFRESH_UPDATE update) {
		
	}

	@Override
	public void OnATStreamTradeUpdate(ATQUOTESTREAM_TRADE_UPDATE update) {
		String strSymbol = new String(update.symbol.symbol);
		int plainSymbolIndex = strSymbol.indexOf((byte) 0);
		strSymbol = strSymbol.substring(0, plainSymbolIndex);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, update.lastDateTime.hour);
		cal.set(Calendar.MINUTE, update.lastDateTime.minute);
		cal.set(Calendar.SECOND, update.lastDateTime.second);
		cal.set(Calendar.MILLISECOND,update.lastDateTime.milliseconds);
		long lastDateTime = cal.getTimeInMillis();
		
		List<Tick.TickField> fields = new ArrayList<Tick.TickField>();
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldSymbol).setType(TickFieldType.STRING).setStringValue(strSymbol).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldLastPrice).setType(TickFieldType.DOUBLE).setDoubleValue(update.lastPrice.price).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldLastSize).setType(TickFieldType.LONG).setLongValue(update.lastSize).build());
		fields.add(Tick.TickField.newBuilder().setId(TradeTicks.FieldLastTradeDateTime).setType(TickFieldType.LONG).setLongValue(lastDateTime).build());
		
		Tick tick = Tick.newBuilder().setType(TradeTicks.TickTrade).addAllTickFields(fields).build();
		provider.streamTick(tick);
	}
	
	

}

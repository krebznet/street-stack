package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.google.common.base.Ticker;

public class EStreamSessionTicker extends EStreamSessionEvent {

	private TradeTickerSpec ticker; 
	private DTime time; 
	private String node; 
	
	
	public EStreamSessionTicker(StreamSession session,TradeTickerSpec ticker,DTime time) {
		super(session);
		this.ticker = ticker;
		this.time = time;
	}
	
	
	public DTime getTime() {
		return time;
	}

	public void setTime(DTime time) {
		this.time = time;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}


	public TradeTickerSpec getTicker() {
		return ticker;
	}


	public void setTicker(TradeTickerSpec ticker) {
		this.ticker = ticker;
	}
	
	

	
	
}

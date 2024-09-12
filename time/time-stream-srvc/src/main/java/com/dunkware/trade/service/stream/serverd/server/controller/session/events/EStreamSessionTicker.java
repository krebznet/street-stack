package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import java.time.LocalTime;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class EStreamSessionTicker extends EStreamSessionEvent {

	private TradeTickerSpec ticker; 
	private LocalTime time; 
	private String node; 
	
	
	public EStreamSessionTicker(StreamSession session,TradeTickerSpec ticker,LocalTime time) {
		super(session);
		this.ticker = ticker;
		this.time = time;
	}
	
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
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

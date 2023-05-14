package com.dunkware.trade.service.beach.server.runtime.core;

import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class BeachTradeSpec {
	
	private int size; 
	private double capital; 
	private Instrument instrument; 
	private String side; 
	private BeachPlay play;
	private TradeTickerSpec tickerSpec; 
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public BeachPlay getPlay() {
		return play;
	}
	public void setPlay(BeachPlay play) {
		this.play = play;
	}
	public TradeTickerSpec getTickerSpec() {
		return tickerSpec;
	}
	public void setTickerSpec(TradeTickerSpec tickerSpec) {
		this.tickerSpec = tickerSpec;
	} 
	
	
	
	

}

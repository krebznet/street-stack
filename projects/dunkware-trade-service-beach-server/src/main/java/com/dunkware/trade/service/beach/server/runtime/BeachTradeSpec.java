package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.service.beach.model.BeachTradeStrategy;
import com.dunkware.trade.tick.api.instrument.Instrument;

public class BeachTradeSpec {

	private Instrument instrument; 
	private TradeSide side; 
	private double capital;
	private BeachTradeStrategy strategy;
	private String source;
	
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public TradeSide getSide() {
		return side;
	}
	public void setSide(TradeSide side) {
		this.side = side;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public BeachTradeStrategy getStrategy() {
		return strategy;
	}
	public void setStrategy(BeachTradeStrategy strategy) {
		this.strategy = strategy;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	} 
	
	
}

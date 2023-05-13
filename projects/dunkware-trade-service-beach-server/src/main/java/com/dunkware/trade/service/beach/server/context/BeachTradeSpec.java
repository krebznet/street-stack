package com.dunkware.trade.service.beach.server.context;

import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.trade.service.beach.server.runtime.BeachTradeExit;
import com.dunkware.trade.tick.api.instrument.Instrument;

public class BeachTradeSpec {
	
	private int size; 
	private double capital; 
	private Instrument instrument; 
	private String side; 
	private BeachTradeOpen entry; 
	private BeachTradeExit exit; 
	private BeachPlay play;
	
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
	public BeachTradeOpen getEntry() {
		return entry;
	}
	public void setEntry(BeachTradeOpen entry) {
		this.entry = entry;
	}
	public BeachTradeExit getExit() {
		return exit;
	}
	public void setExit(BeachTradeExit exit) {
		this.exit = exit;
	}
	public BeachPlay getPlay() {
		return play;
	}
	public void setPlay(BeachPlay play) {
		this.play = play;
	} 
	
	

}

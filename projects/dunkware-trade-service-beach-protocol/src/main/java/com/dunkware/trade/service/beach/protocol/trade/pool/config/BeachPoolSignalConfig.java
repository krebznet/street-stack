package com.dunkware.trade.service.beach.protocol.trade.pool.config;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

public class BeachPoolSignalConfig {

	private String signal; 
	private double capital; 
	private EntryType entry; 
	private ExitType exit; 
	private String stream; 
	private int openTradeLimit;
	
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public EntryType getEntry() {
		return entry;
	}
	public void setEntry(EntryType entry) {
		this.entry = entry;
	}
	public ExitType getExit() {
		return exit;
	}
	public void setExit(ExitType exit) {
		this.exit = exit;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public int getOpenTradeLimit() {
		return openTradeLimit;
	}
	public void setOpenTradeLimit(int openTradeLimit) {
		this.openTradeLimit = openTradeLimit;
	} 
	
	
	
}

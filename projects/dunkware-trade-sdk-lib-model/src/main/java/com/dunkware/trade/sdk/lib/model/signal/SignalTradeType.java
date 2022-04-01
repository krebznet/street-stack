package com.dunkware.trade.sdk.lib.model.signal;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

public class SignalTradeType {

	private String signal; 
	private double capital; 
	private EntryType entry; 
	private ExitType exit; 
	private int openSymbolMax = -1;
	private int openTradeMax = 2;
	
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
	public int getOpenSymbolMax() {
		return openSymbolMax;
	}
	public void setOpenSymbolMax(int openSymbolMax) {
		this.openSymbolMax = openSymbolMax;
	}
	public int getOpenTradeMax() {
		return openTradeMax;
	}
	public void setOpenTradeMax(int openTradeMax) {
		this.openTradeMax = openTradeMax;
	}
	
	
	
	
	
	
	
	
	
}

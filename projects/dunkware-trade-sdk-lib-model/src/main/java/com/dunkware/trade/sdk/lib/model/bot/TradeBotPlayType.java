package com.dunkware.trade.sdk.lib.model.bot;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;


public class TradeBotPlayType  {
	
	private String name; 
	private TradeSide tradeSide; 
	private double capital;
	private EntryType entyType; 
	private ExitType exitType;
	private String signal; 
	private String stream; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TradeSide getTradeSide() {
		return tradeSide;
	}
	public void setTradeSide(TradeSide tradeSide) {
		this.tradeSide = tradeSide;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
		
	public EntryType getEntyType() {
		return entyType;
	}
	public void setEntyType(EntryType entyType) {
		this.entyType = entyType;
	}
	public ExitType getExitType() {
		return exitType;
	}
	public void setExitType(ExitType exitType) {
		this.exitType = exitType;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	} 
	
	
	
	
	
	
	
	
	

}

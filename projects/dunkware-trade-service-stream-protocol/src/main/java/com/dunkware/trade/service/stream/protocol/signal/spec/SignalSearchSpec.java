package com.dunkware.trade.service.stream.protocol.signal.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class SignalSearchSpec {
	
	private String stream; 
	private DDate startDate; 
	private DDate stopDate; 
	private DTime startTime; 
	private DTime stopTime; 
	private List<String> signals = new ArrayList<String>();
	private List<String> symbols = new ArrayList<String>();
	
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public DDate getStartDate() {
		return startDate;
	}
	public void setStartDate(DDate startDate) {
		this.startDate = startDate;
	}
	public DDate getStopDate() {
		return stopDate;
	}
	public void setStopDate(DDate stopDate) {
		this.stopDate = stopDate;
	}
	public DTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}
	public DTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(DTime stopTime) {
		this.stopTime = stopTime;
	}
	public List<String> getSignals() {
		return signals;
	}
	public void setSignals(List<String> signals) {
		this.signals = signals;
	}
	public List<String> getSymbols() {
		return symbols;
	}
	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}
	
	

}

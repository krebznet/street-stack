package com.dunkware.trade.net.service.streamstats.server.statcache;

import java.time.LocalDate;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class StreamEntityVarStatsSession {
	
	private DDate date; 
	private Number high; 
	private Number low; 
	private int valueCount; 
	private DTime highTIme; 
	private DTime lowTIme;
	private String ident; 
	
	
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	public Number getHigh() {
		return high;
	}
	public void setHigh(Number high) {
		this.high = high;
	}
	public Number getLow() {
		return low;
	}
	public void setLow(Number low) {
		this.low = low;
	}
	public int getValueCount() {
		return valueCount;
	}
	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	public DTime getHighTIme() {
		return highTIme;
	}
	public void setHighTIme(DTime highTIme) {
		this.highTIme = highTIme;
	}
	public DTime getLowTIme() {
		return lowTIme;
	}
	public void setLowTIme(DTime lowTIme) {
		this.lowTIme = lowTIme;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	} 
	
	
	
	

}

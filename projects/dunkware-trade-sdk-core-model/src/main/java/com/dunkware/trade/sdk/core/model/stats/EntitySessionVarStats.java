package com.dunkware.trade.sdk.core.model.stats;

import com.dunkware.common.util.dtime.DTime;

public class EntitySessionVarStats {

	private int id; 
	private String ident; 
	
	private double high; 
	private double low; 
	
	private DTime highTime; 
	private DTime lowTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public DTime getHighTime() {
		return highTime;
	}
	public void setHighTime(DTime highTime) {
		this.highTime = highTime;
	}
	public DTime getLowTime() {
		return lowTime;
	}
	public void setLowTime(DTime lowTime) {
		this.lowTime = lowTime;
	} 
	
	
}

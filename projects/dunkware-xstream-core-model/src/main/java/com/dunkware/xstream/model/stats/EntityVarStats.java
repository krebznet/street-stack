package com.dunkware.xstream.model.stats;

import com.dunkware.common.util.dtime.DTime;

public class EntityVarStats {

	private int id; 
	private String ident; 
	
	private Double high = null; 
	private Double low = null; 
	
	private DTime highTime = null;
	private DTime lowTime = null;
	
	
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

	public Double getHigh() {
		return high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public void setHigh(Double high) {
		this.high = high;
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

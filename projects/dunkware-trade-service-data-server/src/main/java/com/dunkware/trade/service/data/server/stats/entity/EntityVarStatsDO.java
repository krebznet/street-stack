package com.dunkware.trade.service.data.server.stats.entity;

import java.time.LocalTime;

import com.ibm.icu.math.BigDecimal;

public class EntityVarStatsDO {
	
	private int id; 
	private String ident; 
	
	private BigDecimal low; 
	private BigDecimal high; 
	
	private LocalTime lowTime; 
	private LocalTime highTime;
	
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
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public LocalTime getLowTime() {
		return lowTime;
	}
	public void setLowTime(LocalTime lowTime) {
		this.lowTime = lowTime;
	}
	public LocalTime getHighTime() {
		return highTime;
	}
	public void setHighTime(LocalTime highTime) {
		this.highTime = highTime;
	} 
	
	
	
	//private 

}

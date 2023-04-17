package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalTime;

public class EntityStatsSessionDocVar {
	
	private int id;
	private String ident;
	private Number high = null;
	private Number low = null;
	private LocalTime highTime;
	private LocalTime lowTime;
	private String highStamp;
	private String lowStamp;
	private int valueCount;
	
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
	public LocalTime getHighTime() {
		return highTime;
	}
	public void setHighTime(LocalTime highTime) {
		this.highTime = highTime;
	}
	public LocalTime getLowTime() {
		return lowTime;
	}
	public void setLowTime(LocalTime lowTime) {
		this.lowTime = lowTime;
	}
	public String getHighStamp() {
		return highStamp;
	}
	public void setHighStamp(String highStamp) {
		this.highStamp = highStamp;
	}
	public String getLowStamp() {
		return lowStamp;
	}
	public void setLowStamp(String lowStamp) {
		this.lowStamp = lowStamp;
	}
	public int getValueCount() {
		return valueCount;
	}
	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	
	

}

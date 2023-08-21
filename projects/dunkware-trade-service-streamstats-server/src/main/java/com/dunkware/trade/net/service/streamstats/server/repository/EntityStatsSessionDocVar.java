package com.dunkware.trade.net.service.streamstats.server.repository;

import java.time.LocalDateTime;

public class EntityStatsSessionDocVar {
	
	private int id;
	private String ident;
	private int dataType; 
	private String high = null;
	private String low = null;
	private LocalDateTime highTime;
	private LocalDateTime lowTime;
	private String highTimeString;
	private String lowTimeString;
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
	
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public LocalDateTime getHighTime() {
		return highTime;
	}
	public void setHighTime(LocalDateTime highTime) {
		this.highTime = highTime;
	}
	public LocalDateTime getLowTime() {
		return lowTime;
	}
	public void setLowTime(LocalDateTime lowTime) {
		this.lowTime = lowTime;
	}
	
	public String getHighTimeString() {
		return highTimeString;
	}
	public void setHighTimeString(String highTimeString) {
		this.highTimeString = highTimeString;
	}
	public String getLowTimeString() {
		return lowTimeString;
	}
	public void setLowTimeString(String lowTimeString) {
		this.lowTimeString = lowTimeString;
	}
	public int getValueCount() {
		return valueCount;
	}
	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	
	

}

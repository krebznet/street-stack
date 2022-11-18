package com.dunkware.xstream.model.stats;

import com.dunkware.common.util.dtime.DDate;

public class SignalStats {

	private DDate date;
	private int sidId;
	private String sigIdent; 
	private int count;
	
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	public int getSidId() {
		return sidId;
	}
	public void setSidId(int sidId) {
		this.sidId = sidId;
	}
	public String getSigIdent() {
		return sigIdent;
	}
	public void setSigIdent(String sigIdent) {
		this.sigIdent = sigIdent;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
}

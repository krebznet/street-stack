package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EntityStatsSessionVarDep {

	private int varId;
	private String varIdent;
	private Number high = null;
	private LocalDateTime highT;
	private LocalDateTime lowT;
	private Number low = null;
	private int values;

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

	public int getVarId() {
		return varId;
	}

	public void setVarId(int varId) {
		this.varId = varId;
	}

	public String getVarIdent() {
		return varIdent;
	}

	public void setVarIdent(String varIdent) {
		this.varIdent = varIdent;
	}

	public LocalDateTime getHighT() {
		return highT;
	}

	public void setHighT(LocalDateTime highT) {
		this.highT = highT;
	}

	public LocalDateTime getLowT() {
		return lowT;
	}

	public void setLowT(LocalDateTime lowT) {
		this.lowT = lowT;
	}

	public int getValues() {
		return values;
	}

	public void setValues(int values) {
		this.values = values;
	}
	
	

	
	

	
	
	

	

}

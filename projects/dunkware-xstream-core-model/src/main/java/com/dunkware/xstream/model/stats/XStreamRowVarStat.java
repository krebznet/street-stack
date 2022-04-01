package com.dunkware.xstream.model.stats;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.value.DValue;


public class XStreamRowVarStat {

	private String stat; 
	private String varName; 
	private int varId;
	private String rowId;
	private DTime time = null;
	private DDate date;
	private DValue<?> value;
	
	public XStreamRowVarStat() { 
		
	}
	
	
	public String getStat() {
		return stat;
	}


	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public int getVarId() {
		return varId;
	}
	public void setVarId(int varId) {
		this.varId = varId;
	}
	public DTime getTime() {
		return time;
	}
	public void setTime(DTime time) {
		this.time = time;
	}
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	public DValue<?> getValue() {
		return value;
	}
	public void setValue(DValue<?> value) {
		this.value = value;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	
	
	
	
	
}

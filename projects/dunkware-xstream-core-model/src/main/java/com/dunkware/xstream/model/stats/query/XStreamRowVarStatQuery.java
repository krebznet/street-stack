package com.dunkware.xstream.model.stats.query;

import com.dunkware.common.util.dtime.DDate;

public class XStreamRowVarStatQuery {

	private String stat;
	private String rows;
	private DDate startDate;
	private DDate endDate;
	private String stream; 
	private String vars;
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public DDate getStartDate() {
		return startDate;
	}
	public void setStartDate(DDate startDate) {
		this.startDate = startDate;
	}
	public DDate getEndDate() {
		return endDate;
	}
	public void setEndDate(DDate endDate) {
		this.endDate = endDate;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getVars() {
		return vars;
	}
	public void setVars(String vars) {
		this.vars = vars;
	}
	
	
}

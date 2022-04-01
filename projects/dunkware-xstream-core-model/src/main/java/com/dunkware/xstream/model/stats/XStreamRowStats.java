package com.dunkware.xstream.model.stats;

import java.util.List;

import com.dunkware.common.util.dtime.DDate;

public class XStreamRowStats {

	private String rowId; 
	private DDate date; 
	private List<XStreamRowVarStat> varStats;
	
	public XStreamRowStats() { 
		
	}
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	public List<XStreamRowVarStat> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<XStreamRowVarStat> varStats) {
		this.varStats = varStats;
	}
	
	
	// stream/stats/session/row
	// stream/stats/session/all
	// stream/stats/session/var
	

}

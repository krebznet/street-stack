package com.dunkware.xstream.model.stats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class XStreamRowVarStats {
	
	private String varName;
	private int varId; 
	private String row; 
	private DDate startDate;
	private DDate endDate;
	private DTime startTime;
	private DTime endTime;
	
	private List<XStreamRowVarStat> stats = new ArrayList<XStreamRowVarStat>();

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

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
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

	public DTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}

	public DTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DTime endTime) {
		this.endTime = endTime;
	}

	public List<XStreamRowVarStat> getStats() {
		return stats;
	}

	public void setStats(List<XStreamRowVarStat> stats) {
		this.stats = stats;
	}
	
	

}

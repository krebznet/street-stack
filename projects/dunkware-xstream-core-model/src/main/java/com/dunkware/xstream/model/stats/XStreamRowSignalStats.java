package com.dunkware.xstream.model.stats;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class XStreamRowSignalStats {

	private String signal;
	private int count;
	private String row; 
	private DDate startDate;
	private DDate endDate;
	private DTime startTime; 
	private DTime endTime;
	
	
	public String getSignal() {
		return signal;
	}
	
	public void setSignal(String signal) {
		this.signal = signal;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
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
	
	
	
	
	
	
}

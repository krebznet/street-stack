package com.dunkware.trade.service.stream.protocol.controller.capture;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class SignalQueryReq {

	private String stream;
	private List<String> rows = new ArrayList<String>();
	private List<String> types = new ArrayList<String>();
	private DDate startDate;
	private DDate endDate;
	private DTime startTime;
	private DTime endTime;
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public List<String> getRows() {
		return rows;
	}
	public void setRows(List<String> rows) {
		this.rows = rows;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
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

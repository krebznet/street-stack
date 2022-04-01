package com.dunkware.trade.service.stream.protocol.controller.capture;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class RowSnapshotReq {
	
	private String row; 
	private DDate date; 
	private String stream; 
	private DTime time;
	
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public DTime getTime() {
		return time;
	}
	public void setTime(DTime time) {
		this.time = time;
	} 
	
	

}

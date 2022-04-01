package com.dunkware.trade.service.data.json.search;

import com.dunkware.common.util.dtime.DDate;

public class DateRange extends CalendarRange {
	
	private DDate start; 
	private DDate stop;
	
	public DDate getStart() {
		return start;
	}
	public void setStart(DDate start) {
		this.start = start;
	}
	public DDate getStop() {
		return stop;
	}
	public void setStop(DDate stop) {
		this.stop = stop;
	} 
	
	

}

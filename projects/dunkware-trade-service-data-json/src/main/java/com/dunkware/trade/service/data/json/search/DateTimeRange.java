package com.dunkware.trade.service.data.json.search;

import com.dunkware.common.util.dtime.DDateTime;

public class DateTimeRange extends CalendarRange  {
	
	private DDateTime start; 
	private DDateTime stop;
	public DDateTime getStart() {
		return start;
	}
	public void setStart(DDateTime start) {
		this.start = start;
	}
	public DDateTime getStop() {
		return stop;
	}
	public void setStop(DDateTime stop) {
		this.stop = stop;
	} 
	
	

}

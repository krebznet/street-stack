package com.dunkware.trade.service.data.service.stream.session;

import com.dunkware.common.util.dtime.DTime;
import com.ibm.icu.impl.duration.TimeUnit;

public interface DataStreamSessionSearch {
	
	
	public Object getEntitySignalReport(String ident);
	
	public Object[] getEntityValues(DTime start, DTime stop, int interval, TimeUnit timeUnit);
	
	public Object entityStream(); // look at variable and already filter shit out. 

}

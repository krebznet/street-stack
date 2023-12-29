package com.dunkware.xstream.api;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.util.dtime.DTime;

public interface XStreamTickRouter extends TickStream {

	public void registerDataTick(int type, int keyField);
	
	public long getTimeTickCount();
	
	public long getDataTickCount();
	
	public DTime getLastDataTickTime();
	
	public long lastTickDelay();
	
}

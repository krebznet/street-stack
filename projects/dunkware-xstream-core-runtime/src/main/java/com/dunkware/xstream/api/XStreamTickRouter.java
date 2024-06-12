package com.dunkware.xstream.api;

import java.time.LocalDateTime;

import com.dunkware.utils.tick.stream.TickStream;

public interface XStreamTickRouter extends TickStream {

	public void registerDataTick(int type, int keyField);
	
	public long getTimeTickCount();
	
	public long getDataTickCount();
	
	public LocalDateTime getLastDataTickTime();
	
	public long lastTickDelay();
	
}

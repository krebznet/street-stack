package com.dunkware.common.tick.stream;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.filter.TickFilter;
import com.dunkware.common.tick.proto.TickProto.Tick;

/**
 * Simple structure for routing or transporting ticks
 * Does not deal with any kind of threading just simple
 * construct. 
 * 
 * @author dkrebs
 *
 */
public interface TickStream {
	
	void addTickHandler(TickHandler handler);
	
	void addTickHandler(TickHandler handler, TickFilter tickFilter);
	
	void removeTickHandler(TickHandler handler);
	
	void streamTick(Tick tick);

	long getTickCount();
	
}

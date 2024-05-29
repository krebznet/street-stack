package com.dunkware.utils.tick.stream;

import com.dunkware.utils.tick.TickHandler;
import com.dunkware.utils.tick.filter.TickFilter;
import com.dunkware.utils.tick.proto.TickProto.Tick;

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
	
	void consume(Tick tick);

	long getTickCount();
	
}

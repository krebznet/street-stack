package com.dunkware.utils.tick.stream;

import com.dunkware.utils.tick.stream.impl.TickStreamImpl;

public class TickStreamFactory {

	public static TickStreamImpl newStream() { 
		return new TickStreamImpl();
	}
	
}

package com.dunkware.common.tick.stream;

import com.dunkware.common.tick.stream.impl.TickStreamImpl;

public class TickStreamFactory {

	public static TickStreamImpl newRouter() { 
		return new TickStreamImpl();
	}
	
}

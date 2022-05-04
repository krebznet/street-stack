package com.dunkware.logger.core;

public class DunkLogBuilder {
	
	public DunkLogBuilder(DunkLogFactory factory) { 
		
	}
	
	public DunkLogBuilder message(String message) { 
		return this; 
	}
	
	public void build() { 
		// send it to DunkLogger
	}

}

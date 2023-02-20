package com.dunkware.net.cluster.node;

public interface DunkChannel {

	void event(Object event);
	
	Object service(Object request);
	
	void close();
	
	
}

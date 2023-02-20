package com.dunkware.net.cluster.node;

public interface DunkNode {

	public String getId(); 
	
	void message(Object payload) throws Exception;

	Object service(Object payload) throws Exception;
	
	// createChannel 
	
	
}

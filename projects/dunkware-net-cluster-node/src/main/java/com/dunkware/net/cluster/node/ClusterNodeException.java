package com.dunkware.net.cluster.node;

public class ClusterNodeException extends Exception {

	private static final long serialVersionUID = 179245528640931695L;

	public ClusterNodeException(String s) { 
		super(s);
	}
	
	public ClusterNodeException(String s, Throwable t) { 
		super(s,t);
	}
	
	
}

package com.dunkware.spring.cluster;

import io.vertx.core.Future;

public interface DunkNetServiceResponse extends Future<DunkNetServiceResponse> {

	public <T> T getResponse();
	
	public String getServiceNodeId();
	
	long geRequestTime();
	
	String getError();
	
	boolean isError();
	
	
}

package com.dunkware.spring.cluster.core.response;

public interface DunkNetServiceResponse {

	public void exception(String error);
	
	public void reply(Object reply);
}

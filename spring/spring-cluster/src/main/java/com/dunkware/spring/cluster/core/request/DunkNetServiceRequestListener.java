package com.dunkware.spring.cluster.core.request;

public interface DunkNetServiceRequestListener {

	public void onServiceReqTimeout(DunkNetServiceRequest req);
	
	public void onServiceReqError(DunkNetServiceRequest req);
	
	public void onServiceReqDone(DunkNetServiceRequest req);
}

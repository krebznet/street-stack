package com.dunkware.trade.service.web.server.grpc.proxy;

public interface GrpcAutoCompleteProxyListener {
	
	
	public void onResponse(String response);
	
	public void onError(Throwable t); 
	
	public void onComplete();

}

package com.dunkware.xstream.net.client;

public interface StreamClientEntitySearchCallBack {
	
	public void onResponse(StreamClientEntitySearch search); 
	
	public void onException(StreamClientEntitySearch search);
	
	public void onComplete(StreamClientEntitySearch search);
	
	

}

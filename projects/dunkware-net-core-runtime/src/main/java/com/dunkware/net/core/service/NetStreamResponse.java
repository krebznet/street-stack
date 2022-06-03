package com.dunkware.net.core.service;

import com.dunkware.net.core.data.NetBean;

public interface NetStreamResponse {
	
	public boolean hasException();
	
	public String getException();
	
	public int getRequestId();
	
	public int getChannelId();
	
	public NetBean getData();

}

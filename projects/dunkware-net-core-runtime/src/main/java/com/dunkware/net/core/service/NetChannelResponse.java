package com.dunkware.net.core.service;

import com.dunkware.net.core.data.NetBean;

public interface NetChannelResponse {
	
	public boolean hasException();
	
	public String getException();
	
	public int getRequestId();
	
	public int getChannelId();
	
	public NetBean getData();

}

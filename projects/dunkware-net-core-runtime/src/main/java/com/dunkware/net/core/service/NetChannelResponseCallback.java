package com.dunkware.net.core.service;

import com.dunkware.net.proto.net.GNetChannelResponse;

public interface NetChannelResponseCallback {

	public void onResponse(GNetChannelResponse response);
	
	public void onError(Throwable t);
}

package com.dunkware.net.core.service;

import com.dunkware.net.proto.net.GNetCallResponse;

public interface NetCallResponseCallback {

	public void onResponse(GNetCallResponse resp);
	
	public void onError(Throwable t);
}

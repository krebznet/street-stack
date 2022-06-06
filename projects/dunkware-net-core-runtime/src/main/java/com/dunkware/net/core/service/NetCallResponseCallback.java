package com.dunkware.net.core.service;

import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetMessage;

public interface NetCallResponseCallback {

	public void onSuccess(GNetCallResponse response);
	
	public void onError(GNetCallResponse response);
}

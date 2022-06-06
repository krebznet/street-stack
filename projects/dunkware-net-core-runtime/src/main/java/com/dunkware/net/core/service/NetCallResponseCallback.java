package com.dunkware.net.core.service;

import com.dunkware.net.proto.net.GNetMessage;

public interface NetCallResponseCallback {

	public void onSuccess(GNetMessage response);
	
	public void onError(GNetMessage response);
}

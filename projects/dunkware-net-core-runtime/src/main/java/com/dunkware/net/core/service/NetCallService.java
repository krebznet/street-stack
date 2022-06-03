package com.dunkware.net.core.service;

public interface NetCallService {

	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException;
}

package com.dunkware.trade.service.web.server.mock.services;

import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.trade.service.web.server.mock.MockServiceStub;


public class MockCall1 implements NetCallService, MockServiceStub   {

	@Override
	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEndpoint() {
		return "/mock/call/1";
	}
	
	
	
	

}

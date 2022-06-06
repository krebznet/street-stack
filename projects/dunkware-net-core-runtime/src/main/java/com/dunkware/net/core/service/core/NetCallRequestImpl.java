package com.dunkware.net.core.service.core;

import com.dunkware.net.core.data.GBeanReader;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.proto.net.GNetCallRequest;

public class NetCallRequestImpl implements NetCallRequest {

	private GNetCallRequest gRequest;
	private GBeanReader beanReader = null;
	
	public NetCallRequestImpl(GNetCallRequest gReq) {
		gRequest = gReq;
		if(gReq.getData() != null) { 
			beanReader = GBeanReader.newInstance(gRequest.getData());
		}
	}
	
	@Override
	public int getRequestId() {
		return gRequest.getRequestId();
	}

	@Override
	public String getEndpoint() {
		return gRequest.getEndPoint();
	}

	@Override
	public GBeanReader getData() {
		return beanReader;
	}

	
	
}

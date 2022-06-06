package com.dunkware.net.core.service.core;

import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.data.NetDataFactory;
import com.dunkware.net.core.service.NetCallResponse;

public class NetCallResponseImpl implements NetCallResponse {

	private NetBean netBean;
	private NetDataFactory dataFactory; 
	
	public NetCallResponseImpl(NetBean bean) { 
		this.netBean  = bean;
	}
	
	@Override
	public NetBean getNetBean() {
		return netBean;
	}

	
}

package com.dunkware.trade.service.beach.server.netservice;

import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetServiceException;

public class TradeServices implements NetCallService {

	@Override
	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException {
		String reqType = req.getData().getString("reqType");
		if(reqType.equals("addTwsBroker")) { 
			String host = req.getData().getString("twsHost");
			int port  = req.getData().getInt("twsPort");
			String name = req.getData().getString("twsName");
			// add a broker 
		}
		
		
	}

	
}

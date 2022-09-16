package com.dunkware.trade.service.beach.server.web.util;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.tws.model.TwsBrokerType;

import comm.dunkware.trade.service.beach.web.model.BeachWebBroker;

public class BeachWebConverter {

	
	public static BrokerType toServerBrokerType(BeachWebBroker broker) throws Exception { 
		if(broker.getType().equalsIgnoreCase("interactive")) { 
			TwsBrokerType type = new TwsBrokerType(); 
			type.setHost(broker.getHost());
			type.setIdentifier(broker.getName());
			type.setPort(broker.getPort());
			type.setConnectionId(1);
			return type; 
		}
		throw new Exception("Web to Server Conversion Does now about broker type " + broker.getType());
	}
	
	
	
}

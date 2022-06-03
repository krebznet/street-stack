package com.dunkware.trade.service.stream.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.anot.AClusterNetCallService;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;

@AClusterNetCallService(endpoint = "/stream/matcher/entity")
public class EntityMatcherService implements NetCallService {

	@Autowired()
	private StreamControllerService streamService;
	
	@Override
	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException {
		// GetMatcher 
		// SaveMatcher 
		// DeleteMatcher
		// UpdateMatcher
		// GetMatcherRefs
		String streamIdent = req.getString("stream");
		
		String reqType = req.getString("reqType");
		if(reqType.equals("getMatcher")) { 
			int matcherId = req.getInt("matcherId");
			// find the matcher 
		}
		if(reqType.equals("getMatcherRefs")) { 
			// List Getmatchers
		}
	}

	
}

package com.dunkware.trade.service.stream.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.anot.AClusterNetCallService;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;

@AClusterNetCallService(endpoint = "/stream/spec")
public class StreamSpecService implements NetCallService {

	@Autowired
	private StreamControllerService streamService;
	
	@Override
	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException {
		String streamIdent = req.getString("stream");
		StreamController stream = null;
		try {
			stream = streamService.getStreamByName(streamIdent);
			//StreamSpec 
			// 
		} catch (Exception e) {
			throw new NetServiceException("Stream " + streamIdent + " Not Found");
		} 
	
		
	}

	
}

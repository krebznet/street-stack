package com.dunkware.trade.service.stream.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.anot.AClusterNetStreamService;
import com.dunkware.net.core.channel.NetChannel;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.net.core.service.NetStreamRequest;
import com.dunkware.net.core.service.NetStreamResponse;
import com.dunkware.net.core.service.NetStreamService;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;

@AClusterNetStreamService(endpoint = "/stream/session/search/entity")
public class EntitySessionSearchService implements NetStreamService {

	@Autowired
	private SessionContainerService containerService; 
	
	@Override
	public void service(NetStreamRequest req, NetStreamResponse response, NetChannel channel) throws NetServiceException {
		String streamIdent = req.getString("stream");
		SessionContainer container; 
		container =  containerService.getStreamContainer(streamIdent);
		if(container == null) { 
			throw new NetServiceException("Stream Session Container Not Found For " + streamIdent);
		}
		// response.setSuccess();
		// response.setOkay()
		// response.validate();
	
	}
	
	
	private class StreamEntitySearch extends Thread { 
		
		/// will have to do that. 
		// we wiill need a list 
		// net bean insert 
		
	}

	
}

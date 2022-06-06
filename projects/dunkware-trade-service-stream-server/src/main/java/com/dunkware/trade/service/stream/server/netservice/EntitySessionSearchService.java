package com.dunkware.trade.service.stream.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.core.anot.ANetCallService;
import com.dunkware.net.core.channel.NetChannel;
import com.dunkware.net.core.service.NetChannelRequest;
import com.dunkware.net.core.service.NetChannelResponse;
import com.dunkware.net.core.service.NetChannelService;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;

@ANetCallService(endpoint = "/stream/session/search/entity")
public class EntitySessionSearchService implements NetChannelService {

	@Autowired
	private SessionContainerService containerService; 
	
	@Override
	public void service(NetChannelRequest req, NetChannelResponse response, NetChannel channel) throws NetServiceException {
		String streamIdent = req.getData().getString("sream");
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

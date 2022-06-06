package com.dunkware.trade.service.web.server.netservice.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.core.util.GNetFactory;
import com.dunkware.net.proto.net.GNetChannelRequest;
import com.dunkware.net.proto.net.GNetMessage;

public class NetSessionChannel implements Runnable {
	
	@Autowired
	private Cluster cluster; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int channelId; 
	private GNetChannelRequest req; 
	private NetSession session; 
	
	
	public void start(GNetChannelRequest req, NetSession session) {
		this.req = req;
		this.session = session;
	}
	
	
	
	@Override
	public void run() {
		try {
			if(cluster.hasChannelNode(req.getEndPoint()) == false) { 
				GNetMessage response = GNetFactory.channelResponseError("Service Endpoint " + req.getEndPoint() + " not registered", req.getRequestId());
				try {
					session.sendMessage(response);
				} catch (Exception e) {
					logger.error("NetSession Channel sending response error failed to send message {} " + e.toString(),req.getRequestId());
				}
				return;
			}
		} catch (Exception e) {
			logger.error("Outer channel request error " + e.toString());
		}
		// okay source needs to be set on all client message: uses web service to get that value. 
	//	cluster.netChannel(null, null);
	}



	public void close() { 
		
	}

}

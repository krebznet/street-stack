package com.dunkware.trade.service.web.server.netservice.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.core.service.NetCallResponseCallback;
import com.dunkware.net.core.service.NetChannelResponseCallback;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetChannelResponse;
import com.dunkware.net.proto.net.GNetCode;
import com.dunkware.net.proto.net.GNetMessage;

public class NetSessionCall {
	
	@Autowired
	private Cluster cluster;
	
	private GNetCallRequest req; 
	private NetSession session; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void start(GNetCallRequest req, NetSession session) { 
		
		if(cluster.hasCallNode(req.getEndPoint()) == false) { 
			
			GNetMessage.newBuilder().setCallResp(GNetCallResponse.newBuilder().
					setCode(GNetCode.ERROR).setException("Service Call Endpoint " + req.getEndPoint() 
					+ " not registered").build()).build();
		}
			//session.senmessage
		// else call and invoke
	}
	
	public void handle(GNetCallRequest request, NetSession session) throws Exception  { 
		
		cluster.netCall(req.getEndPoint(), req.getRequestId(), new NetCallResponseCallback() {
			
			@Override
			public void onSuccess(GNetMessage response) {
				try {
					session.sendMessage(response);	
				} catch (Exception e) {
					logger.error("Exception sending net call response back to web client " + e.toString());
				}
				
			}
			
			@Override
			public void onError(GNetMessage response) {
				try {
					session.sendMessage(response);	
				} catch (Exception e) {
					logger.error("Exception sending net call response back to web client " + e.toString());
				}
			}
		});
	

}
}

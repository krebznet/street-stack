package com.dunkware.net.cluster.node.internal.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetMessageHandler;
import com.dunkware.net.core.service.core.NetCallRequestImpl;
import com.dunkware.net.core.service.core.NetCallResponseImpl;
import com.dunkware.net.core.util.GNetHelper;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetCode;
import com.dunkware.net.proto.net.GNetMessage;

/**
 * this should be executed in a runnable from the parent. 
 * @author duncankrebs
 *
 */
public class NetCallRequestRunner implements NetMessageHandler  {

	@Autowired
	Cluster cluster; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void handle(final GNetMessage message) {
		if(GNetHelper.isCallRequest(message) == false) {
			return;
		}
		GNetCallRequest callReq = message.getCallReq();
		
		ClusterNode requestNode = null;
		
		try {
			requestNode = cluster.getNode(callReq.getSource());
		} catch (Exception e) {
			logger.error("Exception executin cluster net call the source node is not found " + callReq.getSource());
			// now way of sending that oh well;
			return;
		}
		final ClusterNode finalNode = requestNode;
		
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				GNetCallResponse.Builder responseBuilder = GNetCallResponse.newBuilder();
				responseBuilder.setRequestId(callReq.getRequestId());
				
				try {
					NetCallService service = cluster.netCallService(message.getCallReq().getEndPoint());
					NetBean netBean = cluster.netDataFactory().createBean(message.getCallReq().getRequestId());
					NetCallResponseImpl response = new NetCallResponseImpl(netBean);
					NetCallRequestImpl callRequest = new NetCallRequestImpl(message.getCallReq());
					try {
						service.service(callRequest,response);
						responseBuilder.setCode(GNetCode.OKAY);
						responseBuilder.setData(response.getNetBean().convert());
						finalNode.sendNetMessage(GNetMessage.newBuilder().setCallResp(responseBuilder.build()).build());
					} catch (Exception e) {
						 responseBuilder.setCode(GNetCode.ERROR);
						 responseBuilder.setException("Service Exception Catch " + e.toString());
						 GNetCallResponse resp = responseBuilder.build();
						 finalNode.sendNetMessage(GNetMessage.newBuilder().setCallResp(resp).build());
					}
					
				} catch (Exception e) {
					logger.error("Outer Exception in net calll request runner " + e.toString());
				}
			}
		};
		cluster.getExecutor().execute(runner);
		
	}
	
	

}

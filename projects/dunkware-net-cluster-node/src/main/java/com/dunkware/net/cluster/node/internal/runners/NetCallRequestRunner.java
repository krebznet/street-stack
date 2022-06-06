package com.dunkware.net.cluster.node.internal.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.bitch.BitchLogger;
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

	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Cluster cluster;
	
	public NetCallRequestRunner(Cluster cluster) { 
		this.cluster = cluster;
	}
	@Override
	public void handle(final GNetMessage message) {
		
		BitchLogger.log("NetCallRequestRunner handling message");
		if(GNetHelper.isCallRequest(message) == false) {
			BitchLogger.log("NetCallRequest runner not call request");
			return;
		}
		GNetCallRequest callReq = message.getCallReq();
		
		ClusterNode requestNode = null;
		
		try {
			BitchLogger.log("call back node id is " + callReq.getSource());
			requestNode = cluster.getNode(callReq.getSource());
			if(requestNode == null)
			BitchLogger.log("got no callback node from soruce");
		} catch (Exception e) {
			logger.error("Exception executin cluster net call the source node is not found " + callReq.getSource());
			// now way of sending that oh well;
			return;
		}
		final ClusterNode finalNode = requestNode;
		
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				BitchLogger.log("in runnable gettign ready to call net call service");
				GNetCallResponse.Builder responseBuilder = GNetCallResponse.newBuilder();
				responseBuilder.setRequestId(callReq.getRequestId());
				
				try {
					BitchLogger.log("calling cluster netcallservice to get it");
					NetCallService service = cluster.netCallService(message.getCallReq().getEndPoint());
					if(service == null) { 
						BitchLogger.log("Net Call service came back null we have no service to call wetf");
						
					}
					NetBean netBean = cluster.netDataFactory().createBean(message.getCallReq().getRequestId());
					NetCallResponseImpl response = new NetCallResponseImpl(netBean);
					NetCallRequestImpl callRequest = new NetCallRequestImpl(message.getCallReq());
					try {
						BitchLogger.log("calling service calss " + service.getClass().getName());
						service.service(callRequest,response);
						BitchLogger.log("service returned no exception");
						responseBuilder.setCode(GNetCode.OKAY);
						responseBuilder.setData(response.getNetBean().convert());
						BitchLogger.log("sending back response message to node " + finalNode.getId());
						finalNode.sendNetMessage(GNetMessage.newBuilder().setCallResp(responseBuilder.build()).build());
					} catch (Exception e) {
						BitchLogger.log("sendback back error to " + finalNode.getId());
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

package com.dunkware.net.cluster.node.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.bitch.BitchLogger;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallResponseCallback;
import com.dunkware.net.core.service.NetMessageHandler;
import com.dunkware.net.core.util.GNetFactory;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetCode;
import com.dunkware.net.proto.net.GNetMessage;
import com.dunkware.net.proto.net.GNetMessage.ValueCase;

public class ClusterNetCall implements NetCallResponse, Runnable, NetMessageHandler {

	private GNetMessage requestMessage;
	private GNetCallResponse responseMessage;

	private ClusterNode node;

	@Autowired
	private Cluster cluster;

	private int requestId;
	private String endpoint;
	private NetCallResponseCallback callback;

	private Logger logger = LoggerFactory.getLogger(getClass());

	// timeout would be nice.

	public void invoke(String endpoint, int requestId, ClusterNode node, NetCallResponseCallback callback) {
		this.node = node;
		this.callback = callback;
		this.requestId = requestId;
		this.endpoint = endpoint;
	}

	@Override
	public void run() {
		BitchLogger.log("Starting Cluster Net call on node " + cluster.getNodeId() + " to " + endpoint);
		cluster.addNetMessageHandler(ClusterNetCall.this);
		try {
			if(logger.isDebugEnabled()) { 
				logger.debug(MarkerFactory.getMarker("NETCALL"), "Invoking request to node {}  endpoint {}",node.getId(), endpoint);
			}
			GNetMessage mes = GNetFactory.callRequest(endpoint, requestId, cluster.getNodeId());
			BitchLogger.log("Sending GNetMessage net call request to node " + node.getId());
			node.sendNetMessage(mes);
			BitchLogger.log("GNetMessage sent");
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("NETCALL"), "Exception sending message to node {}  endpoint {}  " + e.toString(),node.getId(),endpoint);
			cluster.removeNetMessageHandler(this);
			GNetMessage respM = GNetFactory.callResponseError(requestId,
					"Internal error forwarding request to cluster node " + e.toString());
			callback.onError(respM.getCallResp());
			cluster.removeNetMessageHandler(ClusterNetCall.this);
			return;
		}
	}

	@Override
	public void handle(GNetMessage message) {
		if (message.getValueCase() == ValueCase.CALLRESP) {
			if (message.getCallResp().getRequestId() == requestId) {
				responseMessage = message.getCallResp();
				if (responseMessage.getCode() == GNetCode.ERROR) {
					callback.onError(message.getCallResp());
				} else {
					callback.onSuccess(message.getCallResp());
				}
			}
			cluster.removeNetMessageHandler(ClusterNetCall.this);
		}
	}

	@Override
	public NetBean getNetBean() {
		// TODO Auto-generated method stub
		return null;
	}

}

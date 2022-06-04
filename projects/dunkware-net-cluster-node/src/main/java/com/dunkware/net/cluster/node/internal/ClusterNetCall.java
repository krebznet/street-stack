package com.dunkware.net.cluster.node.internal;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallResponseCallback;
import com.dunkware.net.core.service.NetMessageHandler;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetCode;
import com.dunkware.net.proto.net.GNetMessage;
import com.dunkware.net.proto.net.GNetMessage.ValueCase;

public class ClusterNetCall implements NetCallResponse, Runnable, NetMessageHandler {

	private NetCallRequest request;
	private ClusterNode node;
	private Cluster cluster;

	private GNetCallResponse gResponseMessage;
	private NetCallResponseCallback callback;

	private int requestId;

	public void invoke(NetCallRequest request, Cluster cluster, ClusterNode node, NetCallResponseCallback callback) {
		this.node = node;
		this.request = request;
		this.cluster = cluster;
		this.callback = callback;
		this.requestId = DRandom.getRandom(1, 59999);

	}

	@Override
	public void run() {
		cluster.addNetMessageHandler(ClusterNetCall.this);
		GNetCallRequest req = GNetCallRequest.newBuilder().setEndPoint(request.getEndpoint())
				.setData(request.getData().toProtoBean()).setRequestId(requestId).build();
		GNetMessage reqMe = GNetMessage.newBuilder().setCallReq(req).build();
		try {
			node.sendNetMessage(reqMe);
		} catch (Exception e) {
			cluster.removeNetMessageHandler(this);
			callback.onError(new Exception("Exception sending net call request " + e.toString()));
		}
	}

	@Override
	public boolean hasException() {
		if (gResponseMessage == null) {
			return false;
		}
		if (gResponseMessage.getCode() == GNetCode.ERROR) {
			return true;
		}
		return false;
	}

	@Override
	public String getException() {
		return gResponseMessage.getException();
	}

	@Override
	public int getRequestId() {
		return requestId;
	}

	@Override
	public NetBean getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setException(String exception) {

	}

	@Override
	public void setString(String field, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDouble(String field, Double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJson(String field, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(GNetMessage message) {
		if (message.getValueCase() == ValueCase.CALLRESP) {
			if (message.getCallResp().getRequestId() == request.getRequestId()) {
				gResponseMessage = message.getCallResp();
				if (gResponseMessage.getCode() == GNetCode.ERROR) {
						callback.onError(new Throwable("Server Returned Error on response " + gResponseMessage.getException()));
				}
				else // its okay 
					callback.onResponse(gResponseMessage);
			}
			cluster.removeNetMessageHandler(ClusterNetCall.this);
		}
	}

}

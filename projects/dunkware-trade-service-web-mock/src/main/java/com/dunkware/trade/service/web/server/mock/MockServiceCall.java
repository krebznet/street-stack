package com.dunkware.trade.service.web.server.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.data.NetDataFactory;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.core.NetCallRequestImpl;
import com.dunkware.net.core.service.core.NetCallResponseImpl;
import com.dunkware.net.proto.data.GBean;
import com.dunkware.net.proto.net.GNetCallRequest;
import com.dunkware.net.proto.net.GNetCallResponse;
import com.dunkware.net.proto.net.GNetCode;
import com.dunkware.net.proto.net.GNetMessage;
import com.dunkware.trade.service.web.server.netservice.session.NetSession;

public class MockServiceCall {
	
	private GNetCallRequest req ;
	private NetCallService service; 
	
	private DExecutor executor; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private NetSession session;
	public void execute(DExecutor executor, GNetCallRequest req, NetCallService service, NetSession session) { 
		this.executor = executor; 
		this.session = session;
		this.req = req; 
		this.service = service; 
		NetCallRequestImpl netReq = new NetCallRequestImpl(req);
		NetDataFactory dataFactory = NetDataFactory.newInstance(executor);
		NetBean respBean = dataFactory.createBean(req.getRequestId());
		NetCallResponseImpl netResp = new NetCallResponseImpl(respBean);
		GNetCallResponse.Builder respBuilder = GNetCallResponse.newBuilder();
		respBuilder.setRequestId(req.getRequestId());
		try {
			service.service(netReq, netResp);
			GBean retBean = netResp.getNetBean().convert();
			respBuilder.setData(retBean);
			respBuilder.setCode(GNetCode.OKAY);
			 session.sendMessage(GNetMessage.newBuilder().setCallResp(respBuilder.build()).build());
		} catch (Exception e) {
			logger.error("Exception calling mock call service " + e.toString());
			respBuilder.setCode(GNetCode.ERROR);
			respBuilder.setException("Service threw exception " + e.toString());
			try {
				 session.sendMessage(GNetMessage.newBuilder().setCallResp(respBuilder.build()).build());			
			} catch (Exception e2) {
				logger.error("whatever can't send a mock response error" + e.toString());
			}
	
		}
	}

}

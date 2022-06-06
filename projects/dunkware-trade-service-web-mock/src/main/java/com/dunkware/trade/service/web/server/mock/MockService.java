package com.dunkware.trade.service.web.server.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetChannelService;
import com.dunkware.net.core.util.GNetFactory;
import com.dunkware.net.proto.net.GNetMessage;
import com.dunkware.trade.service.web.server.mock.services.MockCall1;
import com.dunkware.trade.service.web.server.mock.services.MockCall2;
import com.dunkware.trade.service.web.server.netservice.session.NetSession;



@Service
public class MockService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,NetCallService> mockCalls = new ConcurrentHashMap<String,NetCallService>();
	private Map<String,NetChannelService> mockChannels = new ConcurrentHashMap<String, NetChannelService>();
	
	private DExecutor executor; 
	@PostConstruct
	public void postLoad() { 
		executor = new DExecutor(5);
		MockCall1 call1 = new MockCall1();
		mockCalls.put(call1.getEndpoint(), call1);
		MockCall2 call2 = new MockCall2();
		mockCalls.put(call2.getEndpoint(), call2);
	}
	
	public  void callRequest(GNetMessage request,  NetSession session) { 
		String endpoint = request.getCallReq().getEndPoint();
		NetCallService service = mockCalls.get(endpoint);
		if(service == null) { 
			try {
				session.sendMessage(GNetFactory
						.callResponseError(request.getCallReq().getRequestId(), "Service not found"));		
			} catch (Exception e) {
				logger.error("Error sending error! " + e.toString());;
				return;
			}
			MockServiceCall call = new MockServiceCall();
			call.execute(executor,request.getCallReq(),service,session);
			logger.info("called service call end point " + request.getCallReq().getEndPoint());
		}
		
	}
	
	// how do we mock it up here ... smae thing. 
	

}

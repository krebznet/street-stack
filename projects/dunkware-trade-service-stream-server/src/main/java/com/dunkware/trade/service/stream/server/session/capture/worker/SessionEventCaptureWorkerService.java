package com.dunkware.trade.service.stream.server.session.capture.worker;

import org.springframework.context.annotation.Profile;

import com.dunkware.trade.service.stream.server.session.capture.protocol.SessionEventCaptureWorkerStartReq;

@Profile("MongoEventCapture")
public class SessionEventCaptureWorkerService {
	
	public SessionEventCaptureWorker createWorker(SessionEventCaptureWorkerStartReq req) throws SessionEventCaptureWorkerException { 
		return null;
	}
	
	
	public void disposeWorker(int workderID) throws SessionEventCaptureWorkerException { 
		
	}

}

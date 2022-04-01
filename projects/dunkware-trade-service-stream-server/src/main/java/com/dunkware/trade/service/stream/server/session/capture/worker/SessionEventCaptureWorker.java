package com.dunkware.trade.service.stream.server.session.capture.worker;

import com.dunkware.trade.service.stream.server.session.capture.protocol.SessionEventCaptureWorkerStartReq;

public class SessionEventCaptureWorker {
	
	private SessionEventCaptureWorkerStartReq request; 
	
	public SessionEventCaptureWorker() {
		
	}
	
	public void start(SessionEventCaptureWorkerStartReq request) throws SessionEventCaptureWorkerException { 
		this.request = request;
		// KafkaEvenetConsumer 
		// MongoDB 
		// Need Some Utility Shit 
	}

}

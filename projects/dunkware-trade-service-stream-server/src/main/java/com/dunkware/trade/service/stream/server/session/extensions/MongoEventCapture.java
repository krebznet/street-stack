package com.dunkware.trade.service.stream.server.session.extensions;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.capture.SessionEventCaptureService;

public class MongoEventCapture implements StreamSessionExtension {

	@Autowired
	private SessionEventCaptureService captureService; 
	
	private StreamSession session; 

	@Override
	public void sessionStarting(StreamSession session) {
		this.session = session;
		
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStarted(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStopping(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStopped(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		// TODO Auto-generated method stub
		
	}
	
	

}

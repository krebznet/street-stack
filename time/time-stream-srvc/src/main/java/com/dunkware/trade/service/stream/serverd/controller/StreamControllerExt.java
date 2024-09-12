package com.dunkware.trade.service.stream.serverd.controller;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public interface StreamControllerExt {

	public void initialize(StreamController controller) throws Exception;
	
	public void sessionStarted(StreamSession session);
	
	public void sessionStopped(StreamSession session);
	
	public void sessionKilled(StreamSession session);
	
}

// faces-widgets-runtime

// DataGridList
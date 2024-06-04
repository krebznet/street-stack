package com.dunkware.stream.controller.stream;

import com.dunkware.stream.controller.stream.session.StreamSession;

public interface StreamControllerExt {

	public void initialize(StreamController controller) throws Exception;
	
	public void sessionStarted(StreamSession session);
	
	public void sessionStopped(StreamSession session);
	
	public void sessionKilled(StreamSession session);
	
}

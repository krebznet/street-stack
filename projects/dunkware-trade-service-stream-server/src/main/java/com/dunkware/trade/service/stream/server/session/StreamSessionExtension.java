package com.dunkware.trade.service.stream.server.session;

public interface StreamSessionExtension {

	public void sessionStarting(StreamSession session);
	
	public void nodeStarting(StreamSessionNode node);
	
	public void nodeStarted(StreamSessionNode node);
	
	public default void nodeStopping(StreamSessionNode node) { 	}
	
	public default void nodeStopped(StreamSessionNode node)  { }
	
	public void sessionStarted(StreamSession session);
	
	public void sessionStopping(StreamSession session);
	
	public void sessionStopped(StreamSession session);
	
	public void nodeStartException(StreamSessionNode node, String exception);
	
}

package com.dunkware.trade.service.stream.server.controller.session;

public interface StreamSessionExtension {

	public default void sessionStarting(StreamSession session) {}
	
	public default void nodeStarting(StreamSessionNode node) {}
	
	public default void nodeStarted(StreamSessionNode node) {}
	
	public default void nodeStopping(StreamSessionNode node) { 	}
	
	public default void nodeStopped(StreamSessionNode node)  { }
	
	public default void sessionStarted(StreamSession session) {}
	
	public default void sessionStopping(StreamSession session) {}
	
	public default void sessionStopped(StreamSession session) {}
	
	public default void nodeStartException(StreamSessionNode node, String exception) {}
	
}

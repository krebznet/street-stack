package com.dunkware.trade.service.stream.server.controller.session;

public interface StreamSessionExtension {

	public static final int SESSION_STARTING = 1; 
	public static final int SESSION_STARTED = 2; 
	public static final int SESSION_STOPPING = 3; 
	public static final int SESSION_STOPPED = 4; 
	public static final int SESSION_NODE_START_EXCEPTION = 5; 
	public static final int NODE_STARTING = 6;
	public static final int NODE_STARTED = 7;
	
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

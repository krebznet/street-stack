package com.dunkware.net.cluster.node;

public class ClusterMessages {

	/**
	 * The Node ID who sent us this message 
	 */
	public static final String SOURCE_NODE = "SOURCE_NODE";
	
	/**
	 * Some node wants to invoke a service in our node handle return
	 */
	public static final String SERVICE_REQUEST = "SERVICE_REQUEST";
	
	/**
	 * Service Response to the node that requested - success payload or exception 
	 */
	public static final String SERVICE_RESPONSE = "SERVICE_RESPONSE";
	
	/**
	 * Saying another node wants to create a channel with us 
	 */
	public static final String CHANNEL_REQUEST =  "CHANNEL_REQUEST";
	
	/**
	 * Our response to a channel request
	 */
	public static final String CHANNEL_RESPONSE = "CHANNEL_RESPONSE";
	
	/**
	 * this is a message on a open channel that is getting sent to us 
	 */
	public static final String CHANNEL_MESSAGE = "CHANNEL_MESSAGE";
	
	
}

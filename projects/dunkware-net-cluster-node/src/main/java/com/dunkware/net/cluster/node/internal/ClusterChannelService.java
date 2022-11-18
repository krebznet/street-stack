package com.dunkware.net.cluster.node.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.cluster.node.ClusterChannel;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.message.MessageTransport;


public class ClusterChannelService implements TransportHandler {
	
	public static final String CLUSTER_CHANNEL_MESSAGE = "CLUSTER_CHANNEL_MESSAGE";
	
	public static final String CLUSTER_CHANNEL_REQUEST = "CLUSTER_CHANNEL_REQUEST";
	
	public static final String CLUSTER_CHANNEL_RESPONSE = "CLUSTER_CHANNEL_RESPONSE";
	
	public static final String CLUSTER_CHANNEL_DISPOSE = "CLUSTER_CHANNEL_DISPOSE";

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ClusterImpl cluster; 
	
	private Map<String,ClusterChannel> channels = new ConcurrentHashMap<String, ClusterChannel>();
	
	public void start(ClusterImpl cluster) { 
		this.cluster = cluster; 
	}
	
	/**
	 * Okay here we want to create a channel with a target node, we create a 
	 * cluster channel request. 
	 * @param node
	 * @param type
	 * @return
	 */
	public ClusterChannel createChannel(ClusterNode node, String type) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		
	}
	
	
	@Override
	public boolean handler(MessageTransport transport) {
		if(transport.getHeaders().get(CLUSTER_CHANNEL_MESSAGE) != null) { 
			String channelId = (String)transport.getHeaders().get(CLUSTER_CHANNEL_MESSAGE);
			// okay so its an incoming channel message 
		}
		// TODO Auto-generated method stub
		return false;
	}

}

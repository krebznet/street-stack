package com.dunkware.net.cluster.node.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.cluster.node.ClusterChannel;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.channel.ChannelException;
import com.dunkware.spring.channel.core.ChannelImpl;
import com.dunkware.spring.message.Message;

public class ClusterChannelImpl extends ChannelImpl implements ClusterChannel {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ClusterNode targetNode; 
	private String channelId; 
	private String channelType; 
	
	/**
	 * Okay so we started what does this mean? 
	 * we should be a message handle
	 * @param targetNode
	 */
	public void start(ClusterNode targetNode, String channelId, String channelType) {
		this.targetNode = targetNode;
		this.channelId = channelId; 
		this.channelType = channelType;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Object payload) throws ChannelException {
		// 
	}

	@Override
	public void send(Message message) throws ChannelException {
		// okay here we add header 
		message.getHeaders().put(ClusterChannelService.CLUSTER_CHANNEL_MESSAGE, channelId);
		// then we send it through targetNode 
		
	}

	@Override
	public Message sendReply(Object payload) throws ChannelException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public ClusterNode getNode() {
		return targetNode;
	}


	
	
}

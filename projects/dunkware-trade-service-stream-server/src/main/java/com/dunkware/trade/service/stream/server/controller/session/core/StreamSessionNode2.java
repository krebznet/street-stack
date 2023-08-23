package com.dunkware.trade.service.stream.server.controller.session.core;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelListener;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.DunkNetNode;

public class StreamSessionNode2 extends DunkNetComponent implements DunkNetChannelListener {

	private DunkNetChannel channel;
	
	private DunkNetNode netNode;
	
	public void init(DunkNetChannel channel, DunkNetNode node) throws Exception { 
		this.channel = channel;
		this.netNode  = node; 
		this.channel.addListener(this);;
		this.channel.notifyInit();
	}

	@Override
	public void channelOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}
	
	
}

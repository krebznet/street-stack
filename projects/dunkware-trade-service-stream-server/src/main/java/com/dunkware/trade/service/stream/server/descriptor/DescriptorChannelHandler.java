package com.dunkware.trade.service.stream.server.descriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;


public class DescriptorChannelHandler implements DunkNetChannelHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DescriptorChannelHandler");
	
	private DunkNetChannel channel; 
	
	// Entity// StreamEntity
	
	public void init() { 
		
	}
	
	
	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel; 
	}

	@Override
	public void channelStart() throws DunkNetException {
		// what we need here is 
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelStartError(String exception) {
		// TODO Auto-generated method stub
		
	}
	
	

}

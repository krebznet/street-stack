package com.dunkware.trade.service.stream.server.controller.session.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.xstream.model.scanner.XStreamEntityScannerModel;


public class StreamSessionEntityScannerNode implements DunkNetChannelHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityScanner");
	
	private StreamSessionNode sessionNode; 
	
	private DunkNetChannel scannerChannel;
	
	public void start(StreamSessionNode node, XStreamEntityScannerModel model) {
		this.sessionNode = node; 
		//node.getChannel().channel()
	}
	
	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelStart() throws DunkNetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelStartError(String exception) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Tells the the thing to send an update of all 
	 * things in there. 
	 */
	public void snapshotUpdate() { 
		
	}
	
	public void dispose() { 
		// 
	}

}

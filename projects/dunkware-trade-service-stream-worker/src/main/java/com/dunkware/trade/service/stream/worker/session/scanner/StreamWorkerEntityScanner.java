package com.dunkware.trade.service.stream.worker.session.scanner;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetChannelListener;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.trade.service.stream.json.worker.scanner.StreamEntityScannerReq;
import com.dunkware.trade.service.stream.json.worker.scanner.StreamEntityScannerStopReq;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.xstream.api.XStreamEntityQuery;

public class StreamWorkerEntityScanner implements DunkNetChannelHandler  {
	
	private DunkNetChannel channel;
	private StreamWorker workerNode; 
	private StreamEntityScannerReq req; 
	private XStreamEntityQuery query; 
	
	
	public void init(StreamWorker workerNode, StreamEntityScannerReq req) throws Exception { 
		this.workerNode = workerNode;
		this.req = req;
		//query = workerNode.getStream().entityQuery(req.getQuery());
		
	}

	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		this.channel.addExtension(this);
	}

	@Override
	public void channelStart() throws DunkNetException {
		// so this will start after channel client inits
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}
	
	
	@ADunkNetEvent
	public void stopScanner(StreamEntityScannerStopReq req) { 
		
	}

	@Override
	public void channelStartError(String exception) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}

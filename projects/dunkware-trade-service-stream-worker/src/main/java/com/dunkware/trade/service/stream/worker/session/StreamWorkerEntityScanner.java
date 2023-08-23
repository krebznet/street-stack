package com.dunkware.trade.service.stream.worker.session;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelListener;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.trade.service.stream.json.worker.scanner.StreamEntityScannerReq;
import com.dunkware.xstream.api.XStreamEntityQuery;

public class StreamWorkerEntityScanner extends DunkNetComponent implements DunkNetChannelListener  {
	
	private DunkNetChannel channel;
	private StreamWorkerNode workerNode; 
	private StreamEntityScannerReq req; 
	private XStreamEntityQuery query; 
	
	public void init(DunkNetChannel channel, StreamWorkerNode workerNode, StreamEntityScannerReq req) throws Exception { 
		this.channel = channel;
		this.workerNode = workerNode;
		this.req = req;
		query = workerNode.getXStream().entityQuery(req.getQuery());
		
	}
	
	public void onClose() { 
		// require it. 
	}

	@Override
	public void channelOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}
	
	
	private class ScanUpdate implements Runnable {

		@Override
		public void run() {
			
		} 
		
		
		
		
	}

}

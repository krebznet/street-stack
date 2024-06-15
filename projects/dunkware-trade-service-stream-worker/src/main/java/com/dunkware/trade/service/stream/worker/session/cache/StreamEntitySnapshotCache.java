package com.dunkware.trade.service.stream.worker.session.cache;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.xstream.api.XStream;

public class StreamEntitySnapshotCache implements StreamWorkerExtension  {

	private StreamWorker worker; 
	private XStream stream; 
	
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
	}

	@Override
	public void start() throws Exception {
		this.stream = worker.getStream();
		// we are going to have a standard to get the 
		// TEV:1:3 -> going to be a map of values -> 
		
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}

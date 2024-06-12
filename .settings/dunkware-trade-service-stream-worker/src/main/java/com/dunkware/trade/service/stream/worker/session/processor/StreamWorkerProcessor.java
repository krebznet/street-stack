package com.dunkware.trade.service.stream.worker.session.processor;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;

/**
 * Ends Here Duncan. Stream Processor, all your stats, snapshots, publishing etc. 
 * @author Duncan Krebs 
 *
 */
@AStreamWorkerExtension
public class StreamWorkerProcessor implements StreamWorkerExtension {

	private StreamWorker worker; 
	private XStream stream; 
	
	
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
	}

	@Override
	public void start() throws Exception {
		this.stream = worker.getStream();
		
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}

package com.dunkware.trade.service.stream.worker.session.publish;

import org.apache.kafka.clients.producer.Producer;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XSignal;
import com.dunkware.xstream.api.XSignalListener;
import com.dunkware.xstream.api.XStream;

@AStreamWorkerExtension
public class EntitySignalPublisher implements StreamWorkerExtension, XSignalListener  {

	private StreamWorker worker; 
	private XStream stream; 
	
	private Producer<Integer, byte[]> producer;
	
	
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker; 
	}

	@Override
	public void start() throws Exception {
		this.stream = worker.getStream();
		stream.getXSignals().addSignalListener(this);
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignal(XSignal signal) {
		System.err.println("Signal in publisher! " + signal.getType().getModel().getName());
		
	}
	
	

	
}

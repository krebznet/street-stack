package com.dunkware.trade.service.stream.worker.session.publish;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.time.data.model.entity.EntitySignalModel;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XSignal;
import com.dunkware.xstream.api.XSignalListener;
import com.dunkware.xstream.api.XStream;


@AStreamWorkerExtension
public class EntitySignalPublisher implements StreamWorkerExtension, XSignalListener  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
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
		 
         try {
             producer = worker.getDunkNet().getKafkaProducer();
         } catch(Exception e) {
        	 logger.error("Exception creating EntitySignalPublisher Producer " + e.toString());;
        	 throw e;
         }
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignal(XSignal signal) {
		System.err.println("Signal in publisher! " + signal.getType().getModel().getName());
		EntitySignalModel sig = new EntitySignalModel();
		sig.setEntity(signal.getEntity().getIdentifier());
		sig.setEntityIdent(signal.getEntity().getId());
		sig.setId(signal.getType().getId());
		sig.setSignalIdent(signal.getType().getName());
		sig.setTimestamp(signal.getTimeStamp());
		sig.setStream(0);
		try {
			//byte[] bytes = 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	
	

	
}

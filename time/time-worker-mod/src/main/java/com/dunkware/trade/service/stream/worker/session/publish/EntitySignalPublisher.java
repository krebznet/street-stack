package com.dunkware.trade.service.stream.worker.session.publish;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.tiime.data.util.constants.StreamDataTopics;
import com.dunkware.time.data.model.entity.EntitySignalModel;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.api.XSignal;
import com.dunkware.xstream.api.XSignalListener;
import com.dunkware.xstream.api.XStream;

//TODO: AVINASHANV-30 Signal Publisher
/**
 * look at the annotation its an extension to the stream worker higher level than XStream 
 * and it registers itself as a signal listener to the XStream and converts it to a model
 * class and sends it to kafak where injestors write it to the database. 
 */
@AStreamWorkerExtension
public class EntitySignalPublisher implements StreamWorkerExtension, XSignalListener  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntitySignalPublisher");
	
	private StreamWorker worker; 
	private XStream stream; 
	
	private AtomicInteger signalCounter = new AtomicInteger();
	private Map<Integer,AtomicInteger> signalTypeCounter = new ConcurrentHashMap<Integer,AtomicInteger>();
	
	private Producer<Integer, byte[]> producer;
	
	private String topicName; 
	
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
		stream.getXSignals().removeSignalListener(this);
	}

	@Override
	public void onSignal(XSignal signal) {
		System.err.println("Signal in publisher! " + signal.getType().getModel().getName());
		EntitySignalModel sig = new EntitySignalModel();
		sig.setEntity(signal.getEntity().getIdentifier());
		sig.setSignal(signal.getType().getId());
		sig.setStream((int)stream.getInput().getId());
		sig.setTimestamp(signal.getTimeStamp());
		sig.setVars(signal.getNumericVariables()); 
		sig.setStream((int)stream.getInput().getId());
		try {
			   try {
                   byte[] bytes = DunkJson.getObjectMapper().writeValueAsBytes(sig);
                   ProducerRecord<Integer,byte[]> record = new ProducerRecord<Integer,byte[]>(StreamDataTopics.CaptureEntitySignal,bytes);
                   producer.send(record);
                   signalCounter.incrementAndGet();
                   AtomicInteger typeCounter = signalTypeCounter.get(signal.getType().getId());
                   if(typeCounter == null) { 
                	   typeCounter = new AtomicInteger();
                   }
                   typeCounter.incrementAndGet();
                   signalTypeCounter.put(signal.getType().getId(), typeCounter);
               } catch (Exception e) {
                   logger.error(marker, "Exception sending signal byytes " + e.toString());
               }
			
		} catch (Exception e) {
			   logger.error(marker, "Exception sending signal byytes " + e.toString());
		}
		
		
		
		
	}
	
	

	
}

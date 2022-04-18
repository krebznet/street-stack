package com.dunkware.net.cluster.node.trace;

import static org.mockito.Mockito.inOrder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.net.cluster.GTrace;

@Service 
public class TraceService {
	
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Value("${net.cluster.core.brokers}")
  private String brokers;
  private String topic = "cluster-trace";
  @Value("${net.cluster.core.node}")
  private String node; 
  
  private DKafkaByteProducer kafkaProducer;
  private Publisher publisher; 
  
  private BlockingQueue<GTrace> publishQueue = new LinkedBlockingQueue<GTrace>();
  
  @PostConstruct
  private void load() { 
	  try {
		  System.out.println("-------- starting trace service ------");
		kafkaProducer = DKafkaByteProducer.newInstance(brokers, topic, node + "_trace");
	} catch (Exception e) {
		logger.error("Exception building trace kafka producer " + e.toString());
		System.exit(-1);
	}
	 publisher = new Publisher();
	 publisher.start();
	 trace("info").message("fuck you").tag("0023").tag("Stream").label("tradeid", "323").send();
	 
  }
  
  public Trace trace(String level) { 
	  Trace trace = new Trace(this, level);
	  return trace;
  }
  
  public void send(GTrace trace) { 
	  publishQueue.add(trace);
  }
	
  
  public String node() { 
	  return node; 
  }
  
  
  private class Publisher extends Thread { 
	  
	  public void run() { 
		  while(!interrupted()) { 
			  try {
				GTrace trace = publishQueue.take();
				kafkaProducer.sendBytes(trace.toByteArray());
			} catch (Exception e) {
				logger.error("Sending Trace Exception " + e.toString());
			}
		  }
	  }
  }

}

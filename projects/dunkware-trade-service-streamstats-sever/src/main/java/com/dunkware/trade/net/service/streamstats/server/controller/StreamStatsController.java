package com.dunkware.trade.net.service.streamstats.server.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsRequest;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsResponse;
import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsRuntime;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStats;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsService;

/**
 * Going to handle kafka stream stat requests probaby iike severa hundred thousand at a time. 
 * @author duncankrebs
 *
 */
//@Service
public class StreamStatsController implements DKafkaByteHandler2 {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StreamStatsService statsService; 
	
	@Autowired
	private StreamStatsRuntime runtime; 
	
	private DExecutor executor; 
	
	
	@Value("${config.controller.poopers}")
	private String brokers;
	
	@Value("${config.controller.topic}")
	private String topic; 
	
	@Value("${config.controller.group}")
	private String consumerGroup;
	
	@Value("${config.controller.client}")
	private String kafkaClient; 
	
	private DKafkaByteConsumer2 consumer; 
	
	private BlockingQueue<StreamStatsRequest> requestQueue = new LinkedBlockingQueue<StreamStatsRequest>();
			
	private RequestHandler requestHandler; 
	
	private ConcurrentHashMap<String, DKafkaByteProducer> responseProucers = new ConcurrentHashMap<String, DKafkaByteProducer>();
	
	
	private StreamStatsMetrics metrics = new StreamStatsMetrics();
	
	private StreamStatsRunnablePool runnablePool;
	
	@PostConstruct
	private void load() { 
		executor = runtime.getExecutor();
		logger.info("Starting Stream Stats Controller" );
		runnablePool = StreamStatsRunnablePool.create(500);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker("172.16.16.55:31090").setClientAndGroup("dd" + DUUID.randomUUID(5), DUUID.randomUUID(6))
					.addTopic("statreq").build();
			
			consumer = DKafkaByteConsumer2.newInstance(spec);
			consumer.addStreamHandler(this);
			consumer.start();	
		} catch (Exception e) {
			logger.error("Cannot connect to Kafak Consumer Topic, Fatal system crash " );
			System.exit(-1);
		}
		
		requestHandler = new RequestHandler();
		requestHandler.start();
		logger.info("Started Stream Stats Controller");
		
		
	}
	
	
	public StreamStatsService getStatsService() {
		return statsService;
		
	}
	
	public StreamStats getstreamStats(String streamIdent) throws Exception { 
		return statsService.getStreamStats(streamIdent);
	}
	
	
	public StreamStatsMetrics getMetrics() { 
		return metrics; 
	}
	
	
	public void sendResponse(StreamStatsRequest req, StreamStatsResponse response) { 
		DKafkaByteProducer producer = responseProucers.get("statresp");
		if(producer == null) { 
			try {
				producer = DKafkaByteProducer.newInstance(brokers,"statresp",DUUID.randomUUID(5));
				responseProucers.put("statresp",producer);
			} catch (Exception e) {
				logger.error("Exception creating response broker topic producer " + e.toString(),e);
				metrics.setErrorCount(metrics.getErrorCount() + 1);
			}
			
		}
		try {
			byte[] byotes = DJson.serialize(response).getBytes();
			System.out.println("");
			producer.sendBytes(byotes);
			
		} catch (Exception e) {
			logger.error("Exception sending response back over kafka " + e.toString());;
			metrics.setErrorCount(metrics.getErrorCount() + 1);
		}
		
	}
	
	
	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			StreamStatsRequest request = DJson.getObjectMapper().readValue(record.value(), StreamStatsRequest.class);
			requestQueue.add(request);
			metrics.setPendingRequests(requestQueue.size());
			metrics.setRequestCount(metrics.getRequestCount() + 1);
		} catch (Exception e) {
			logger.error("Exception deserializing stats request and adding it to the q " + e.toString());;
			metrics.setErrorCount(metrics.getErrorCount() + 1);
		}
	}


	private class RequestHandler extends Thread { 
		
		public void run() { 
			try {
				setName("Stream Stats Request Handler");
				while(!interrupted()) { 
					StreamStatsRequest req = requestQueue.take();
					StreamStatsRunnable runnaable = runnablePool.acquire();
					runnaable.init(req, StreamStatsController.this);
					int loopcount = 0;
					while(executor.getActiveTaskCount() > 50) { 
						try {
							Thread.sleep(25);
							loopcount++;
							if(loopcount > 8) { 
								logger.error("Exception waiting on Executor");
								loopcount = 0;
							}
						} catch (Exception e) {
							if (e instanceof InterruptedException) { 
								return;
								
							}
							logger.error("request handler" + e.toString());;
						}
					}
					executor.execute(runnaable);
					
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Exception invoking stat runnable " + e.toString(),e);
			}
		}
	}
	
}

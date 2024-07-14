package com.dunkware.stream.data.injest.consumers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.dunkware.stream.data.cassy.loaders.SessionEntityStatLoader;
import com.dunkware.stream.data.injest.config.InjestConfig;
import com.dunkware.tiime.data.util.constants.StreamDataTopics;
import com.dunkware.tiime.data.util.helpers.EntityStatModelHelper;
import com.dunkware.time.data.model.entity.EntitySignalModel;
import com.dunkware.time.data.model.entity.EntityStatModel;
import com.dunkware.time.data.model.entity.EntityStatsModel;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumerSpec;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteHandler;

import jakarta.annotation.PostConstruct;

public class EntitySignalConsumer implements KafkaByteHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Marker marker = MarkerFactory.getMarker("EntitySignalConsumer");
    
    private InjestConfig config;

    private BlockingQueue<EntitySignalModel> kafkaQueue = new LinkedBlockingQueue<EntitySignalModel>();

    private KafkaByteConsumer kafkaConsumer;
    
    private List<ConsumerThread> consumerThreads = new ArrayList<ConsumerThread>();
    
    private AtomicLong recordCounter = new AtomicLong(0);

  
    private SessionEntityStatLoader sessionStatLoader; 
    
    
    
    private CqlSessionBuilder sessionBuilder; 
    
    public EntitySignalConsumer(CqlSessionBuilder sesionFactory, InjestConfig config) {
    	sessionBuilder = sesionFactory;
    	this.config = config;
    }
    

    @PostConstruct
    private void postConstruct() {
          
            Thread init = new Thread() {

                public void run() {
                    try {
                    	
                        KafkaByteConsumerSpec spec = KafkaByteConsumerSpec.newBuilder(KafkaByteConsumerSpec.ConsumerType.Auto, KafkaByteConsumerSpec.OffsetType.Latest)
                                .addBroker(config.getKafkaBrokers()).addTopic(StreamDataTopics.CaptureEntitySignal).setClientAndGroup(config.getKafkaConsumerId(),config.getKafkaConsumerGroup()).setThrottle(50000000).build();
                        kafkaConsumer = KafkaByteConsumer.newInstance(spec);
                        kafkaConsumer.addStreamHandler(EntitySignalConsumer.this);
                        kafkaConsumer.start();
                        
                        sessionStatLoader = new SessionEntityStatLoader(sessionBuilder.build(), config.getSessionEntityStatLoaderThreadCount(), config.getSessionEntityStatLoaderBatchSize());

                        int i = 0;
                        while(i < 9) {
                            ConsumerThread injestor = new ConsumerThread(i);
                            injestor.start();;
                            consumerThreads.add(injestor);
                            i++;
                        }
                
                    } catch(Exception e) {
                        logger.error(marker, "Exceptoin Starting Var Start Kafka Consumer " + e.toString(),e);

                    }

                }

            };

            init.start();

    }
    
    @Override
    public void record(ConsumerRecord<String, byte[]> record) {
        try {
         //  kafkaQueue.add(EntityStatsCodec.decode(record.value()));
        } catch(Exception e) {
            logger.error("Exception Decoding EntityDateStats " + e.toString());
        }
    }
    
    
   
    /**
     * This pulls an EntityStatsModel off the KafkaQueue
     * and processes, we have multiple tables here we need 
     * to right to. 
     */
    private class ConsumerThread extends Thread { 
    	
    	private int id; 
    	
    	public ConsumerThread(int id) { 
    		this.id = id; 
    	}
    	
    	public void run() { 
    		setName("Entity-Stats-Consumer-" + id);
    		while(!interrupted()) { 
    			try {
					
    				//EntityStatsModel model = kafkaQueue.poll(4,TimeUnit.SECONDS);
					/*
					 * if(model == null) { continue; }
					 * 
					 * List<EntityStatModel> statList = EntityStatModelHelper.collect(model);
					 * sessionStatLoader.load(statList);
					 */
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error(marker, "Exception pulling EntityStatsModel From Kafka Queue " + e.toString());
				}
    			
    		}
    	}
    	
    }
    
    
    
    
    
   
}

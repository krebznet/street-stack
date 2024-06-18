package com.dunkware.spring.cluster.core.controllers;

import java.time.LocalDateTime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.core.DunkNetImpl;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;
import com.dunkware.utils.core.helpers.DunkUUID;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumerSpec;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteHandler;


public class DunkNetPingPublisher implements KafkaByteHandler {

	private DunkNetImpl dunkNet;
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DunkNet");

	@Autowired
	private ApplicationContext ac;
	
	private PingSender pingSender; 
	
	private KafkaByteConsumer pingConsumer; 

	public void init(DunkNet net) {
		this.dunkNet =(DunkNetImpl) net;
		pingSender = new PingSender(); 
		pingSender.start();
		
		try {
			String pingTopic = "dunknet." + dunkNet.getConfig().getClusterId() + ".node.ping";
			KafkaByteConsumerSpec spec = KafkaByteConsumerSpec.newBuilder(KafkaByteConsumerSpec.ConsumerType.Auto, KafkaByteConsumerSpec.OffsetType.Latest)
                    .addBroker(dunkNet.getConfig().getServerBrokers()).addTopic(pingTopic).setClientAndGroup(dunkNet.getConfig().getNodeId(),dunkNet.getConfig().getNodeId() + "_" + DunkUUID.randomUUID(5)).setThrottle(500000).build();
            pingConsumer = KafkaByteConsumer.newInstance(spec);
            pingConsumer.addStreamHandler(this);
            pingConsumer.start();
			
			
			
		} catch (Exception e) {
			logger.error(marker, "Exception creating ping consumer");
		}
	}
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			DunkNetNodeDescriptor ping = DunkJson.getObjectMapper().readValue(record.value(), DunkNetNodeDescriptor.class);
			if(logger.isTraceEnabled()) { 
				logger.trace("Recieved Ping on node {} from Node {} with profiles {}",dunkNet.getId(),ping.getId(),ping.getProfiles());
			}
			dunkNet.nodeDescriptor(ping);
		} catch (Exception e) {
			logger.error(marker, "Exception consuming ping " + e.toString());
		}
	}



	private class PingSender extends Thread { 
		
		public void run() {
			setName("DunkNet-PingSender");
			while(!interrupted()) { 
				DunkNetNodeDescriptor ping = new DunkNetNodeDescriptor();
				ping.setDescriptors(dunkNet.extensions().buildDescriptors());
				ping.setTimestamp(LocalDateTime.now());
				ping.setId(dunkNet.getId());
				ping.setProfiles(dunkNet.getConfig().getProfiles());
				dunkNet.sendUpdate(ping);
				try {
					Thread.sleep(1000);	
				} catch (Exception e) {
					logger.error(marker, "Exception sending node update");
				}
				
			}
		}
	}


	

}

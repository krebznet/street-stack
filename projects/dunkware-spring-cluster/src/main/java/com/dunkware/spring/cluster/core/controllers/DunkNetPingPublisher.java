package com.dunkware.spring.cluster.core.controllers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.core.DunkNetImpl;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;


public class DunkNetPingPublisher implements DKafkaByteHandler2 {

	private DunkNetImpl dunkNet;
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DunkNet");

	@Autowired
	private ApplicationContext ac;
	
	private PingSender pingSender; 
	
	private DKafkaByteConsumer2 pingConsumer; 

	public void init(DunkNet net) {
		this.dunkNet =(DunkNetImpl) net;
		pingSender = new PingSender(); 
		pingSender.start();
		
		try {
			String pingTopic = "dunknet." + dunkNet.getConfig().getClusterId() + ".node.ping";
			pingConsumer = DKafkaByteConsumer2.newInstance(DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).setClientAndGroup(dunkNet.getConfig().getNodeId() + 1, dunkNet.getConfig().getNodeId() + 1).addBroker(dunkNet.getConfig().getServerBrokers()).addTopic(pingTopic).build());
			pingConsumer.start();
			pingConsumer.addStreamHandler(this);
			
			
		} catch (Exception e) {
			logger.error(marker, "Exception creating ping consumer");
		}
	}
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			DunkNetNodeDescriptor ping = DJson.getObjectMapper().readValue(record.value(), DunkNetNodeDescriptor.class);
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
				ping.setTimestamp(DDateTime.now());
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
